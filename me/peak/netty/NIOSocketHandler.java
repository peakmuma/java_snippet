package me.peak.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOSocketHandler implements Runnable{

	Logger logger = LoggerFactory.getLogger(NIOSocketHandler.class);

	SocketChannel socketChannel;
	NIOSocketHandler(SocketChannel channel){
		this.socketChannel = channel;
	}
	@Override
	public synchronized void run() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		try {
			int res;
			StringBuilder sb = new StringBuilder();
			buffer.clear();
			logger.info("---------start get message");
			while ( (res = socketChannel.read(buffer)) > 0 ) {
				logger.info("---------get message length " + res);
				buffer.flip();
				while (buffer.position() < buffer.limit()) {//todo < or <= ???
					sb.append((char)buffer.get());
				}
				processMessage(sb.toString());
				buffer.clear();
			}
			if (res == -1) {
				socketChannel.close();
			}
			logger.info("---------get message over");
		} catch (IOException e) {
			logger.error("something error", e);
		}
	}

	public void processMessage(String message){
		System.out.println(message);
	}
}
