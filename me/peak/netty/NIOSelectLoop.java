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

public class NIOSelectLoop implements  Runnable{

    private static Logger logger = LoggerFactory.getLogger(NIOSelectLoop.class);

    private Selector selector;

    private BlockingQueue<SocketChannel> channelQueue;

    public NIOSelectLoop(Selector selector) {
        this.selector = selector;
        channelQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void run() {
        while (NIOServer.serverAlive) {
            try {
                int readyKeyCount = selector.select();
                logger.info("-------readyKeyCount------{}", readyKeyCount);
                SocketChannel channel;
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterable = set.iterator();
                while (iterable.hasNext()){
                    SelectionKey key = iterable.next();
                    Runnable runner = (Runnable) key.attachment();
                    runner.run();
                    iterable.remove();
                }
                while ( (channel = channelQueue.poll()) != null ) {
                    channel.register(selector, SelectionKey.OP_READ, new NIOSocketHandler(channel));
                }
            }catch (IOException e) {
            	logger.error("something error", e);
            }
        }
    }

    public boolean addChannel (SocketChannel channel) {
        return channelQueue.offer(channel);
    }



}
