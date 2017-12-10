package me.peak.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable {

    Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] bytes = new byte[5];
        try {
            InputStream in = socket.getInputStream();
            StringBuilder sb = new StringBuilder("receive message:");
            while ( in.read(bytes) != -1){
                for (byte b : bytes) {
                    if ( b != (byte)4) {
                        sb.append(b);
                    } else {
                        OutputStream out = socket.getOutputStream();
                        out.write(sb.toString().getBytes());
                        sb.setLength(16);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
