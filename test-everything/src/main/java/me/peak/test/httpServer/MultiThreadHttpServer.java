package me.peak.test.httpServer;

import me.peak.test.httpServer.sockethandler.KeepAliveSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadHttpServer {

	private int port = 9080;

	private ExecutorService executorService;

	private String data = "Hello, this is a simple HTTP server use MultiThread.";

	public MultiThreadHttpServer(){
		executorService = Executors.newCachedThreadPool();
	}
	
	public static void main(String[] args){
		MultiThreadHttpServer httpServer = new MultiThreadHttpServer();
		httpServer.start();
	}

	public void start(){
		try {
			ServerSocket serverSocket = new ServerSocket(this.port);
			System.out.println("listen port " + this.port);
			Socket socket = null;
			while(true){
			    socket = serverSocket.accept();
//				executorService.submit(new SocketHandler(socket, data));
				executorService.submit(new KeepAliveSocketHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Start server failed, port occupied!");
		} 
	}
	
}
