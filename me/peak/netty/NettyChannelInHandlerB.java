package me.peak.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NettyChannelInHandlerB extends ChannelInboundHandlerAdapter{

    Logger logger = LoggerFactory.getLogger(NettyChannelInHandlerB.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("this is inHandlerB");
        ctx.write(msg);
    }



}
