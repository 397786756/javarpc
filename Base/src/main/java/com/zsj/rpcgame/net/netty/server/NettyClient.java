package com.zsj.rpcgame.net.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.stereotype.Component;

/**
 * @Description: netty 客户端
 * @Author: zhengsongjie
 * @File: NettyClient
 * @Version: 1.0.0
 * @Time: 2020-11-03 下午 06:31
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.net.netty.server
 * @Software: IntelliJ IDEA
 */
@Component
public class NettyClient {
    private void bind(int port) throws InterruptedException {
        NioEventLoopGroup  group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(group).connect("127.0.0.1", 9527);


    }
}
