package me.peak.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static me.peak.netty.Client.EOF;

public class NIOServer {


	static boolean serverAlive = true;

	public static void main(String[] args) {
		new NIOServer().start(9060);
	}

	public void start(int port) {
		ServerSocketChannel serverSocketChannel = null;
		Selector selector = null;
		try {
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);
			while (serverAlive) {
				int num = selector.select();
				if (num == 0) {
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					if (key.isAcceptable()) {
						handleAccept(key);
					} else if (key.isReadable()) {
						handleRead(key);
					}
					iter.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocketChannel != null) {
					serverSocketChannel.close();
				}
				if (selector != null) {
					selector.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
		key.selector().wakeup();
	}


	private void handleRead(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel)key.channel();
		ByteBuffer buf = (ByteBuffer)key.attachment();
		long count = socketChannel.read(buf);
		StringBuilder message = new StringBuilder();
		while (count > 0) {
			buf.flip();
			while (buf.hasRemaining()) {
				char c = (char)buf.get();
				if ( c != EOF ) {
					message.append(c);
				} else {
					handleWrite(socketChannel, message.toString().getBytes());
					message.setLength(0);
				}
			}
			buf.clear();
			count = socketChannel.read(buf);
		}
		if (count == -1) {
			socketChannel.close();
		}
	}

	private void handleWrite(SocketChannel socketChannel, byte[] message) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(message.length + 1);
		byteBuffer.put(message);
		byteBuffer.put(EOF);
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
	}

}
