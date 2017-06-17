package me.peak.httpserver.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * Created by peak on 2016/12/28.
 */
public class SocketHandler implements Runnable{

    private Socket socket;

    private byte[] header;

    private byte[] body;

    private String bodyStr;

    public SocketHandler(Socket socket, String bodyStr){
        this.socket = socket;
        StatisticUtil.statisticIP(socket);
        int visitCount = StatisticUtil.getVisitCount(socket);
        this.bodyStr = bodyStr + " Your visit count is " + visitCount;
        try {
            body = this.bodyStr.getBytes("UTF-8");
            String headerStr = "HTTP/1.0 200 OK\r\n" +
                    "Server:Simple Server\r\n" +
                    "Content-length:" + body.length + "\r\n" +
                    "Content-type:text/plain\r\n\r\n";
            this.header = headerStr.getBytes();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            StringBuilder request = new StringBuilder();
            InputStream inputStream = socket.getInputStream();
            byte[] buf = new byte[1024];
            while(true){
                int c = inputStream.read();
                if(c != -1){
                    request.append((char)c);
                }else{
                    System.out.println("InputStream read end!");
                    return;
                }
                if(request.indexOf("\r\n\r\n") > 0){
                    break;
                }
            }
            System.out.println("request: " + request.toString());
            OutputStream out = socket.getOutputStream();
            out.write(header);
            out.write(body);
            out.flush();
        } catch (SocketTimeoutException se){
            System.out.println("time out");
            return;
        }catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(socket != null){
                try {
                    System.out.println("socket close");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
