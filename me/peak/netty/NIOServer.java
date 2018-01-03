package me.peak.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

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
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            NIOSelectLoop selectLoop = new NIOSelectLoop(selector);
            key.attach(new NIOAcceptor(serverSocketChannel, selectLoop));

            new Thread(selectLoop,"SelectLoop").start();
            while(serverAlive){
            	Thread.yield();
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
