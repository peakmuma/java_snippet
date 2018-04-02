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
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		try {
			int res;
			StringBuilder sb = new StringBuilder();
			buffer.clear();
			logger.info("---------start get message");
			while ( (res = socketChannel.read(buffer)) > 0 ) {
				logger.info("---------get message length {}", res);
				buffer.flip();
				while (buffer.position() < buffer.limit()) {
					sb.append((char)buffer.get());
				}
				buffer.clear();
				processMessage(sb.toString());
			}
			if (res == -1) {
				socketChannel.close();
			}
			logger.info("---------get message over");
		} catch (IOException e) {
			logger.error("something error", e);
		}
	}

	public void processMessage(final String message){
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(message);
			}
		}).start();
	}
}
