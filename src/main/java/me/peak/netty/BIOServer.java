package me.peak.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    static boolean serverAlive = true;

    static Logger logger = LoggerFactory.getLogger(BIOServer.class);

    public static void main(String[] args) {
        new BIOServer().startServer(9060);
    }

    public void startServer(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            logger.info("listening port {}", port);
            while (serverAlive) {
                Socket socket = serverSocket.accept();
                new Thread(new BIOSocketHandler(socket)).start();
            }
        } catch (IOException e) {
        	logger.error("something error", e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    logger.info("stop listen port {}", port);
                } catch (IOException e) {
                	logger.error("something error", e);
                }
            }
        }
    }
}

        /*我之前的NIO写法是不正确的， 只是用到了select， 并没有很好的实现一个线程监控多个channel
        这个需要实现一下，接收到新请求之后应该注册select上， 后续就交给select去处理了。
        如果有精力的话， 还需要研究一下如何维护多个select，并且保持它们监听数量的平衡。
        */

        /*
        目前我对inputStream里的read方法理解不够深刻。read返回一个-1的时候， 到底对方是发送完了一个包，还是已经关闭
        连接。我感觉按文档说的来， 很有可能是后者， 如果是前者的话， read方法并不会返回， 而是阻塞，
        查了一些资料，-1确实代表了对方关闭连接。 那么问题就变成了如果确定包的边界， 因为流的形式，有数据就会获取到。
        这个就是TCP的粘包问题，基本上办法有三种：固定长度，特殊字符分割，加一个头部，头部里面有字段表示后续包长度
        这个需要想办法研究一下如何得知对方已经关闭链接了， 难道只能通过-1这种方式来？还有很多种
        */

        /*
        需要设计一个实验来证明io和nio的区别，设置100个链接， 每个连接随机发送3，5个请求， 然后把每个请求的响应时间记下来，
        算出99%的返回时间， 算出平均数， 并且统计当时服务端线程的数量
         */

        /*
        select其实是一个系统调用，一个线程调用select，并传入了参数：多个文件描述符， 然后都交个系统了。
        那java又是如何实现select的，操作系统提供的select又是什么样的？
         */
