package me.peak.httpserver;

import me.peak.httpserver.sockethandler.SocketHandler;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by peak on 2016/12/28.
 */
public class SSLHttpServer {


    private Executor executor;

    private SSLContext sslContext;

    private SSLServerSocketFactory sslServerSocketFactory;

    private SSLServerSocket sslServerSocket;

    private static int port = 9443;

    private String data = "Hello, this is a simple HTTPS server.";

    public SSLHttpServer() throws Exception{
        executor = Executors.newCachedThreadPool();
        sslContext = getSSLContext();
        sslServerSocketFactory = sslContext.getServerSocketFactory();
        sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
    }

    public static void main(String[] args){
        try {
            SSLHttpServer server = new SSLHttpServer();
            System.out.println("server start on port: " + port);
            server.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void start(){
        SSLSocket sslSocket = null;
        while (true){
            try {
                sslSocket = (SSLSocket) sslServerSocket.accept();
                SocketHandler socketHandler = new SocketHandler(sslSocket, data);
                executor.execute(socketHandler);
            }catch (Exception e){
               e.printStackTrace();
            }
        }
    }

    public static SSLContext getSSLContext() throws Exception{
        String keystorePath = "E:/httpsserver/key/server.keystore";
        String keyPass = "123456";
        String storePass = "123456";

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(keystorePath), storePass.toCharArray());

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keyPass.toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyManagers, null, null);

        return sslContext;
    }

}
