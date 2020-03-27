package me.peak.httpserver.sockethandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Logger logger = LoggerFactory.getLogger(SwitchSocketHandler.class);

    public SwitchSocketHandler(SocketChannel socketChannel) throws IOException {
        this.clientChannel = socketChannel;
        this.switchChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        this.clientChannel.configureBlocking(false);
        this.switchChannel.configureBlocking(false);
        logger.debug("connect server success");
    }

    public void run() {
        try {
            Selector selector = Selector.open();
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            SelectionKey switchKey = switchChannel.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            while (true) {
                int readChannels = selector.select(60*1000);
                if(readChannels == 0){
                    logger.debug("select result is 0 ,the client channel status is " + clientChannel.isConnected());
                    logger.debug("select result is 0 ,the switch channel status is " + switchChannel.isConnected());
                    break;
                }
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key==clientKey && key.isReadable()){
                        buffer.clear();
                        int read = clientChannel.read(buffer);
                        if(read == -1){
                            clientChannel.close();
                            logger.debug("client channel has been closed --------------------");
                        }
                        while(read > 0){
                            buffer.flip();
                            switchChannel.write(buffer);
                            logger.debug("-------------request------------");
                            printBuffer(buffer.array(),read);
                            buffer.clear();
                            read = clientChannel.read(buffer);
                        }
                    }else if(key==switchKey && key.isReadable()){
                        buffer.clear();
                        int read = switchChannel.read(buffer);
                        if(read == -1){
                            switchChannel.close();
                            logger.debug("switch channel has been closed --------------------");
                        }
                        while(read>0){
                            buffer.flip();
//							printBuffer(buffer.array(),read);
                            clientChannel.write(buffer);
                            logger.debug("-------------response------------length:" + read);
                            buffer.clear();
                            read = switchChannel.read(buffer);
                        }
                    }else{
                        logger.debug("==============something wrong===========");
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(clientChannel.isConnected()) {
                    clientChannel.close();
                    logger.debug("------------client channel close --------------------");
                }
                if(switchChannel.isConnected()) {
                    switchChannel.close();
                    logger.debug("------------switch channel close --------------------");
                }
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
