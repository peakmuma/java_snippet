package me.peak.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
	
	private byte[] content;
	
	private byte[] header;
	
	private int port = 9080;
	
	public SimpleHttpServer() throws UnsupportedEncodingException{
		String data = "Hello, this is a simple HTTP server";
		this.content = data.getBytes("UTF-8");
		String MIMEType = "text/plain";
		String header = "HTTP/1.0 200 OK\r\n" +
						"Server:Simple Server\r\n" +
						"Content-length:" + this.content.length + "\r\n" +
						"Content-type:" + MIMEType + "\r\n\r\n";
		this.header = header.getBytes("ASCII");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		SimpleHttpServer simpleHttpServer = new SimpleHttpServer();
		simpleHttpServer.start();
	}

	public void start(){
		try {
			ServerSocket serverSocket = new ServerSocket(this.port);
			System.out.println("listen port " + this.port);
			while(true){
				Socket socket = serverSocket.accept();
				handleSocket(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Start server failed, port occupied!");
		} 
	}
	
	private void handleSocket(Socket socket){
		try {
			StringBuilder request = new StringBuilder();
			InputStream in = socket.getInputStream();
			while(true){
				int c = in.read();  
				if(c != -1){
					request.append((char)c);
				}else{
					System.out.println("InputStream read end!");
					return;
				}
				if(request.indexOf("\r\n\r\n")>0){
					System.out.println("white line break");
					break;
				}
			}
			System.out.println("request: ");
			System.out.println(request);
			OutputStream out = socket.getOutputStream();
			out.write(this.header);
			out.write(this.content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(socket != null){
				try {
					System.out.println("socket close");
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
