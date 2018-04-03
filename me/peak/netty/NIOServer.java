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
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            selector = Selector.open();
            NIOSelectLoop selectLoop = new NIOSelectLoop(selector);
            new Thread(selectLoop,"SelectLoop").start();

            while(serverAlive){
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
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
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
