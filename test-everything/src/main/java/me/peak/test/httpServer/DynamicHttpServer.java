package me.peak.test.httpServer;

import me.peak.test.httpServer.sockethandler.SwitchSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DynamicHttpServer {

	private int port = 80;

	private ExecutorService executorService;

	Logger logger = LoggerFactory.getLogger(DynamicHttpServer.class);

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
			logger.debug("listen port " + this.port);
			Selector selector = Selector.open();
//			new Thread(new NIOSelectLoop(selector)).start();
			while(true){
				SocketChannel socketChannel = serverSocketChannel.accept();
				SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
//				new SwitchSocketHandler(socketChannel).run();
				executorService.submit(new SwitchSocketHandler(socketChannel));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Start server failed, port occupied!");
		}
	}
	
}
