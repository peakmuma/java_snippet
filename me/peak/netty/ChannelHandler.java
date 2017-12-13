package me.peak.netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelHandler implements Runnable{

	private SocketChannel socketChannel;

	public ChannelHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		try {
			int res;
			StringBuilder sb = new StringBuilder();
			buffer.clear();
			System.out.println("---------start get message");
			while ( (res = socketChannel.read(buffer)) > 0 ) {
				System.out.println("---------get message length " + res);
				buffer.flip();
				while (buffer.position() < buffer.limit()) {//todo < or <= ???
					sb.append((char)buffer.get());
				}
				System.out.println(sb.toString());
				buffer.clear();
			}
			if (res == -1) {
				socketChannel.close();
			}
			System.out.println("---------get message over");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
