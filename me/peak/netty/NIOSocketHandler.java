package me.peak.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOSocketHandler implements Runnable{

	Logger logger = LoggerFactory.getLogger(NIOSocketHandler.class);

	StringBuilder messageSB;

	SocketChannel socketChannel;

	NIOSocketHandler(SocketChannel channel){
		this.socketChannel = channel;
		messageSB = new StringBuilder();
	}

	@Override
	public synchronized void run() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		try {
			int res;
			buffer.clear();
			logger.info("---------start get message");
			while ( (res = socketChannel.read(buffer)) > 0 ) {
				logger.info("---------get message length " + res);
				buffer.flip();
				while (buffer.position() < buffer.limit()) {//todo < or <= ???
					char c = (char)buffer.get();
					if (c != (byte)4) {
						messageSB.append(c);
					} else {
						processMessage(messageSB.toString());
						messageSB.setLength(0);
					}
				}
				buffer.clear();
			}
			if (res == -1) {
				logger.info("---------client close socket");
				socketChannel.close();
			}
			logger.info("---------get message over");
		} catch (IOException e) {
			logger.error("something error", e);
		}
	}

	public void processMessage(String message) throws IOException {
		//process message
	    logger.info(message);
	    //send message
	    byte[] bytes = message.getBytes("UTF-8");
	    ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
	    byteBuffer.put(bytes);
	    byteBuffer.flip();
	    socketChannel.write(byteBuffer);
	}
}
