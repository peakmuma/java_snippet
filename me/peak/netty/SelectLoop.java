package me.peak.netty;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SelectLoop implements  Runnable{

    private Selector selector;


    public SelectLoop(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int readChannels = selector.select();
                if (readChannels == 0) {
                    break;
                }
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        //TODO
                        iterator.remove();
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
