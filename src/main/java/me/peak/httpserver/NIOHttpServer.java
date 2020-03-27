package me.peak.httpserver;

import me.peak.httpserver.common.StatisticUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NIOHttpServer {
	
	private int port = 9080;
	
	private byte[] header;

	private byte[] body;
	
	private ConcurrentHashMap<String, AtomicInteger> statistic = new ConcurrentHashMap<String, AtomicInteger>();
	
	public NIOHttpServer() throws UnsupportedEncodingException{
		String data = "Hello, this is a simple HTTP server use NIO.";
		this.body = data.getBytes("UTF-8");
		String header = "HTTP/1.0 200 OK\r\n" +
						"Server:Simple Server\r\n" +
						"Content-length:" + body.length + "\r\n" +
						"Content-type:text/plain\r\n\r\n";
		this.header = header.getBytes();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		NIOHttpServer httpServer = new NIOHttpServer();
		httpServer.start();
	}

	public void start(){
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
			System.out.println("listen port " + this.port);
			while(true){
				SocketChannel socketChannel = serverSocketChannel.accept();
				handleSocketChannel(socketChannel);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Start server failed, port occupied!");
		} 
	}

	private void handleSocketChannel(SocketChannel socketChannel){
		try {
			StringBuilder request = new StringBuilder();
			ByteBuffer byteBuffer = ByteBuffer.allocate(512);
			int byteCount = socketChannel.read(byteBuffer);
			while(true){
				if(byteCount > 0){
					byteBuffer.flip();
					while(byteBuffer.hasRemaining()){
						request.append((char)byteBuffer.get());
					}
				} else {
					break;
				}
				if(request.indexOf("\r\n\r\n") > 0){
					break;
				}
				byteBuffer.clear();
				byteCount = socketChannel.read(byteBuffer);
			}
			System.out.println("request: " + request.toString());
			byteBuffer.clear();
			byteBuffer.put(this.header);
			byteBuffer.put(this.body);
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(socketChannel != null){
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
