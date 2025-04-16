package me.peak.test.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class BIOSocketHandler implements Runnable {

	static Logger logger = LoggerFactory.getLogger(BIOSocketHandler.class);

    Socket socket;

    public BIOSocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] bytes = new byte[128];
        try {
            socket.setSoTimeout(5000);
            InputStream in = socket.getInputStream();
            in.read(bytes);

            OutputStream out = socket.getOutputStream();
            out.write(bytes);
            StringBuilder sb = new StringBuilder();
            int res;
            while ( (res = in.read(bytes)) != -1) {
            	for (int i = 0; i < res; i++) {
                    if (bytes[i] != (byte) 4) {
                        sb.append((char)bytes[i]);
                    } else {
                        handleReceivedMessage(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
            logger.info("read method return -1");
        } catch (SocketTimeoutException e) {
            logger.info("time out");
        } catch (IOException e) {
        	logger.error("something error", e);
        } finally {
            if (socket != null) {
                try {
                    logger.info("close socket");
                    socket.close();
                } catch (IOException e) {
                    logger.error("close socket error", e);
                }
            }
        }
    }

    private void handleReceivedMessage(String message) throws IOException {
    	if (socket == null) {
    	    throw new IOException("socket is null");
        }
        socket.getOutputStream().write(("hi server receive your message:" + message).getBytes("ASCII") );
    	socket.getOutputStream().write((byte)4);
    	socket.getOutputStream().flush();
    }
}
