package me.peak.test.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

public class Client implements Runnable {

    static Logger log = LoggerFactory.getLogger(Client.class);

    static byte EOF = (byte)4;

    //疑问点:socket的close是否会引起inputStream和outStream的断开
    //out.close(), 会发起一个断开连接的请求.
    public static void main(String[] args) {
		new Thread(new Client(), ""+0).start();
//    	for (int j=1; j<16; j++ ){
//    		new Thread(new Client(), ""+j).start();
//		}
    }

	@Override
	public void run() {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9060); //建立TCP连接的过程。
			socket.setSoTimeout(15000);
			for (int i = 0; i < 8; i++) {
				sendMessage(socket, "Thread" + Thread.currentThread().getName() + ": the No." + i + " message");
				Thread.sleep(2000);
			}
			byte[] bytes = new byte[1024];
			InputStream in = socket.getInputStream();
			StringBuilder sb = new StringBuilder();
			int res;
			log.info("start read");
			while ((res = in.read(bytes)) != -1) {
				for (int i = 0; i < res; i++) {
					if (bytes[i] != EOF) {
						sb.append((char) bytes[i]);
					} else {
						log.info("client receive message: {}", sb.toString());
						sb.setLength(0);
					}
				}
				log.info("start read again");
			}
			log.info("read method return -1");
		} catch (SocketTimeoutException e) {
			log.info("time out");
		} catch (IOException e) {
			log.error("something error", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					log.info("socket close");
					socket.close();              //断开链接
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void sendMessage(Socket socket, String message) throws IOException {
		OutputStream out = socket.getOutputStream();
		out.write(message.getBytes(Charset.forName("ASCII"))); //发送数据
		out.write(EOF);
		out.flush();
		log.info("client send message: {}", message);
	}

	public static void sendMessageFromInput(Socket socket) throws IOException {
//        获取键盘输入
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		OutputStream out = socket.getOutputStream();
		while (true) {
			String str = input.readLine();
			if ("bye".equals(str)) {
				System.out.println("bye bye!");
				break;
			}
			out.write(str.getBytes(Charset.forName("utf-8"))); //发送数据
			System.out.println("send message " + str);
			out.flush();
		}
		out.close();
	}
}
