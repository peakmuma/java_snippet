package me.peak.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class NettyClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",9060);
            //获取键盘输入
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            OutputStream out = socket.getOutputStream();
            while (true) {
                String str = input.readLine();
                if ("bye".equals(str)) {
                    System.out.println("bye bye!");
                    break;
                }
                out.write(str.getBytes(Charset.forName("utf-8")));
                System.out.println("send message " + str);
                out.flush();
            }
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
