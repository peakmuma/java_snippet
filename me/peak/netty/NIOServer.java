package me.peak.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {


    static boolean serverAlive = true;

    public static void main(String[] args) {
        new NIOServer().start(9060);
    }

    public void start(int port){
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            Selector selector = Selector.open();
            SelectLoop selectLoop = new SelectLoop(selector);
            new Thread(selectLoop).start();
            while(serverAlive){
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                //todo 可改进，检查一下返回值，如果返回false, 说明数量太多，需要新建几个selectLoop
                selectLoop.addChannel(socketChannel);
                selector.wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Start server failed!");
        } finally {
            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
