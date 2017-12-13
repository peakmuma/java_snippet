package me.peak.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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
        while (true) {
            try {
                int readChannels = selector.select();
                System.out.println("---------------read channels" + readChannels);
                SocketChannel channel;
                while ( (channel = channelQueue.poll()) != null ) {
                    channel.register(selector, SelectionKey.OP_READ);
                }
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        channel = (SocketChannel) key.channel();
                        System.out.println("--------get channel, the remote port is " + channel.socket().getPort());
                        new Thread(new ChannelHandler(channel)).start();
                        iterator.remove();
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addChannel (SocketChannel channel) {
        return channelQueue.offer(channel);
    }
}
