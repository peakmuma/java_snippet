package me.peak.netty;

import me.peak.httpserver.sockethandler.SwitchSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {

    public void start(int port){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            Selector selector = Selector.open();

            while(true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Start server failed, port occupied!");
        }
    }
}
