package me.peak.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) {
        try {
            new NettyServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void start() throws InterruptedException {
        int port = 9060;
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);// 设置nio类型的channel
            bootstrap.localAddress(new InetSocketAddress(port)); //设置监听地址
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                    ch.pipeline().addLast("myHandler", new NettyChannelHandler());
                    ch.pipeline().addLast("myHandler2", new NettySecondHandler());
                }
            });

            ChannelFuture f = bootstrap.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程
        }
    }

}
