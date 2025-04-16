package me.peak.test.netty;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ZeroCopy {

    public void testSendfile() throws IOException {
        String host = "localhost";
        int port = 9026;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fname = "/Users/youtna/test2";
        long fsize = 183678375L;

        FileChannel fc = new FileInputStream(fname).getChannel();
        long start = System.currentTimeMillis();

        // 核心的硬盘读取和数据发送逻辑
        long curnset =  fc.transferTo(0, fsize, sc);

        System.out.println("total bytes transferred--"+curnset+" and time taken in MS--"+(System.currentTimeMillis() - start));
        fc.close();
    }
}
