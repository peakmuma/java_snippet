package me.peak.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SelectLoop implements  Runnable{

    private static Logger logger = LoggerFactory.getLogger(SelectLoop.class);

    private Selector selector;

    private BlockingQueue<SocketChannel> channelQueue;

    public SelectLoop(Selector selector) {
        this.selector = selector;
        channelQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void run() {
        while (NIOServer.serverAlive) {
            try {
                int readChannels = selector.select();
                System.out.println("-------read channels------" + readChannels);
                SocketChannel channel;
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterable = set.iterator();
                while (iterable.hasNext()){
                    SelectionKey key = iterable.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                        channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        channel = (SocketChannel) key.channel();
						readFromChannel(channel);
//						new Thread(new ChannelHandler(channel)).start();
                    }
                    iterable.remove();
                }
                while ( (channel = channelQueue.poll()) != null ) {
                    channel.register(selector, SelectionKey.OP_READ);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addChannel (SocketChannel channel) {
        return channelQueue.offer(channel);
    }


    public void readFromChannel(SocketChannel socketChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        try {
            int res;
            StringBuilder sb = new StringBuilder();
            buffer.clear();
            System.out.println("---------start get message");
            while ( (res = socketChannel.read(buffer)) > 0 ) {
                System.out.println("---------get message length " + res);
                buffer.flip();
                while (buffer.position() < buffer.limit()) {//todo < or <= ???
                    sb.append((char)buffer.get());
                }
                System.out.println(sb.toString());
                buffer.clear();
            }
            if (res == -1) {
                socketChannel.close();
            }
            System.out.println("---------get message over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
