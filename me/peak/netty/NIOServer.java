package me.peak.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
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
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            SelectLoop selectLoop = new SelectLoop(selector, serverSocketChannel);
            new Thread(selectLoop,"SelectLoop").start();
            while(serverAlive){
            	Thread.yield();
//                SocketChannel socketChannel = serverSocketChannel.accept();
//                socketChannel.configureBlocking(false);
//                //todo 可改进，检查一下返回值，如果返回false, 说明数量太多，需要新建几个selectLoop
//                selectLoop.addChannel(socketChannel);
//
//                selector.wakeup();
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
