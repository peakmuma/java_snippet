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
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            Selector selector = Selector.open();
            new Thread(new SelectLoop(selector)).start();
            while(serverAlive){
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Start server failed, port occupied!");
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
