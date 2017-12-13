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
			int res = 0;
			StringBuilder sb = new StringBuilder();
			buffer.clear();
			System.out.println("---------start get message");
			while ( (res = socketChannel.read(buffer)) > 0 ) {
				System.out.println("---------get message length " + res);
				System.out.println(res == buffer.limit());
				for (int i=0; i < res; i++) {
					System.out.println(buffer.position());
					sb.append(buffer.get());
				}
				System.out.println(sb.toString());
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
