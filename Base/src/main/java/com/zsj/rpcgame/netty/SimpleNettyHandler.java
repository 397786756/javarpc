package com.zsj.rpcgame.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: netty服务器握手连接
 * @Author: zhengsongjie
 * @File: SimpleNettyHandler
 * @Version: 1.0.0
 * @Time: 2020-11-02 下午 06:24
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.netty
 * @Software: IntelliJ IDEA
 */
@ChannelHandler.Sharable
public class SimpleNettyHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
