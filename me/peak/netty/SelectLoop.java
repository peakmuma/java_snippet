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

    private ServerSocketChannel serverSocketChannel;

    private BlockingQueue<SocketChannel> channelQueue;

    public SelectLoop(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
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
                    channel = (SocketChannel) key.channel();
                    if (key.isAcceptable()) {
                         new Thread(new Acceptor()).start();
                    }
                    if (key.isReadable()) {
                        new Thread(new Reader(channel)).start();
                    }
                    if (key.isWritable()) {
                        //TODO 还有一点不太不明确，是否应该将writable从interest set里面去掉？感觉如果删掉会出问题。
                        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                        new Thread(new Writer(channel, byteBuffer)).start();
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

    class Acceptor implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                channel.configureBlocking(false);
                addChannel(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Reader implements Runnable{
        SocketChannel socketChannel;
        Reader(SocketChannel channel){
            this.socketChannel = channel;
        }
        @Override
        public void run() {
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

    class Writer implements Runnable{
        SocketChannel socketChannel;
        ByteBuffer byteBuffer;

        Writer(SocketChannel channel, ByteBuffer byteBuffer) {
            this.socketChannel = channel;
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void run() {
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
