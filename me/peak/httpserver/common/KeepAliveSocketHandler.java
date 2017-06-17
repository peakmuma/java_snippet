package me.peak.httpserver.common;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by peak on 2016/12/31.
 */
public class KeepAliveSocketHandler implements Runnable {

    private Socket socket;


    public KeepAliveSocketHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            socket.setSoTimeout(2*60*1000);
            while (!socket.isClosed()){
                handleSocket();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void handleSocket(){
        try {
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            OutputStream out = socket.getOutputStream();
            byte[] buf = new byte[8192];
            in.mark(buf.length);
            int read = 0;
            int readLength = 0;
            while (true){
                read = in.read(buf,readLength,buf.length-readLength);
                if(read == -1){
                    throw new SocketException("client close socket");
                }
                readLength += read;
                int headerEndIndex = findHeaderEndIndex(buf,readLength);
                if(headerEndIndex > 0){
                    Request request = Request.parseReqFromBytes(buf, headerEndIndex);
                    response(out,request);
                    in.reset();
                    in.skip(headerEndIndex);
                    break;
                }else if(readLength == buf.length){
                    throw new BusinessException("can not find header end Index");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void response(OutputStream out, Request request) throws IOException {
        String path = parsePath(request.getUri());
        String directory = "D:\\blog\\public";
        String MIMEType = parseType(path);
        File file = new File( directory + path);
        if(file.isDirectory()){
            file = new File( directory + path + "index.html");
        }
        long bodyLength = file.length();
        FileInputStream fileIn = new FileInputStream(file) ;
        byte[] buf = new byte[2048];
        int read = 0;
        String headerStr = "HTTP/1.1 200 OK\r\n" +
                "Server:Simple Server\r\n" +
                "Content-length:" + bodyLength + "\r\n" +
                "Content-type:" + MIMEType + "\r\n\r\n";
        byte[] header = headerStr.getBytes();
        out.write(header);
        while( (read = fileIn.read(buf)) != -1){
            out.write(buf,0,read);
        }
    }

    private String parsePath(String url){
        int pathEnd = url.indexOf('?');
        if(pathEnd>0){
            return url.substring(0,pathEnd);
        }
        return url;
    }

    private String parseType(String path){
        int fileNameIndex = path.lastIndexOf('.');
        String fileType = path.substring(fileNameIndex + 1);
        return ServerConst.MIMETypeMap.get(fileType);
    }


    private int findHeaderEndIndex(byte[] buf,  int length) {
        for(int i=0; i<length-3; i++){
            if(buf[i]=='\r' && buf[i+1]=='\n' && buf[i+2]=='\r' && buf[i+3]=='\n'){
                return i+3;
            }
        }
        return 0;
    }
}
