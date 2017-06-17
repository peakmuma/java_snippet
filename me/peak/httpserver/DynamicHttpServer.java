package me.peak.httpserver;

import me.peak.httpserver.common.KeepAliveSocketHandler;
import me.peak.httpserver.common.SwitchSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DynamicHttpServer {

	private int port = 9080;

	private ExecutorService executorService;

	public DynamicHttpServer(){
		executorService = Executors.newCachedThreadPool();
	}
	
	public static void main(String[] args){
		DynamicHttpServer httpServer = new DynamicHttpServer();
		httpServer.start();
	}

	public void start(){

		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
			System.out.println("listen port " + this.port);
			while(true){
				SocketChannel socketChannel = serverSocketChannel.accept();
				executorService.submit(new SwitchSocketHandler(socketChannel));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Start server failed, port occupied!");
		} 
	}
	
}
