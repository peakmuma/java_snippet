package me.peak.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9060); //建立TCP连接的过程。
            sendMessage(socket, "the first message");
            byte[] bytes = new byte[128];
            while (socket.getInputStream().read(bytes) != -1){
                System.out.println(new String(bytes));
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();              //断开链接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendMessage(Socket socket, String message) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes(Charset.forName("utf-8"))); //发送数据
        out.write((byte)4);
        out.flush();
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
