package me.peak.httpserver.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SwitchSocketHandler implements Runnable {

    private SocketChannel clientChannel;

    private SocketChannel switchChannel;

    public SwitchSocketHandler(SocketChannel socketChannel) throws IOException {
        this.clientChannel = socketChannel;
        this.switchChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9080));
        this.clientChannel.configureBlocking(false);
        this.switchChannel.configureBlocking(false);
        System.out.println("-----connect server success----");
    }

    @Override
    public void run() {
        try {
            Selector selector = Selector.open();
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            SelectionKey switchKey = switchChannel.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            while (true) {
                long before = System.nanoTime();
                int readChannels = selector.select();
                long after = System.nanoTime();
                System.out.println("--------select time is: " + (after -  before)/1000000 );
                System.out.println("select result is " + readChannels);
                if(readChannels == 0){
                    System.out.println("select result is 0 ,the client channel status is " + clientChannel.isConnected());
                    System.out.println("select result is 0 ,the switch channel status is " + switchChannel.isConnected());
                    break;
                }
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key==clientKey && key.isReadable()){
                        buffer.clear();
                        int read = clientChannel.read(buffer);
                        while(read > 0){
                            buffer.flip();
                            switchChannel.write(buffer);
                            System.out.println("-------------request------------");
                            printBuffer(buffer.array(),read);
                            buffer.clear();
                            read = clientChannel.read(buffer);
                        }
                    }else if(key==switchKey && key.isReadable()){
                        buffer.clear();
                        int read = switchChannel.read(buffer);
                        while(read>0){
                            buffer.flip();
//							printBuffer(buffer.array(),read);
                            clientChannel.write(buffer);
                            System.out.println("-------------response------------length:" + read);
                            buffer.clear();
                            read = switchChannel.read(buffer);
                        }
                    }else{
                        System.out.println("==============something wrong===========");
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                clientChannel.close();
                System.out.println("------------client chaneel close --------------------");
                switchChannel.close();
                System.out.println("------------switch channel close --------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printBuffer(byte[] buf, int length){
        for(int i=0; i<length; i++){
            System.out.print((char)buf[i]);
        }
        System.out.println();
    }

}
