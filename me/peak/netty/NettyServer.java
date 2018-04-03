package me.peak.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) {
        try {
            new NettyServer().start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //io是啥, nio又是啥
    //如何使用netty进行开发, pom引入jar包, 自己写handler, 如何拼接handler, `
    //netty的运行模型, 如何处理连接, 接收到消息如何处理. 这个是讲解的核心
    public void start() throws InterruptedException {
        int port = 9060;
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventExecutorGroup executors = new DefaultEventExecutorGroup(16);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);// 设置nio类型的channel
            bootstrap.localAddress(new InetSocketAddress(port)); //设置监听地址
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
//                    ch.pipeline().addLast(executors, "encode", new ObjectEncoder());
                    ch.pipeline().addLast(executors,"decode", new FixedLengthFrameDecoder(3));
                    ch.pipeline().addLast(executors,"inHandlerA", new NettyChannelInHandlerA());
                    ch.pipeline().addLast(executors, "inHandlerB", new NettyChannelInHandlerB());
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
