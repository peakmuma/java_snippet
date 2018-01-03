package me.peak.netty;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOAcceptor implements Runnable{

	ServerSocketChannel serverSocketChannel;
	NIOSelectLoop selectLoop;

	NIOAcceptor(ServerSocketChannel serverSocketChannel, NIOSelectLoop selectLoop) {
		this.serverSocketChannel = serverSocketChannel;
		this.selectLoop = selectLoop;
	}

	@Override
	public synchronized void run() {
		try {
			SocketChannel channel = serverSocketChannel.accept();
			channel.configureBlocking(false);
			selectLoop.addChannel(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
