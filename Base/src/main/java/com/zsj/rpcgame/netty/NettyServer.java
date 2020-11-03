package com.zsj.rpcgame.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("nettyServer")
public class NettyServer implements CommandLineRunner, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static Channel serverChannel;

    @Override
    public void run(String... args) throws Exception {
        bind();
    }

    private void bind() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 512).handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast("heart-dog", new IdleStateHandler(30, 30, 60, TimeUnit.SECONDS));
                channel.pipeline().addLast("http-codec", new HttpServerCodec());
                channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                channel.pipeline().addLast("aggregator", new HttpObjectAggregator(65535));
                channel.pipeline().addLast(new WebSocketFrameAggregator(65535));
                channel.pipeline().addLast("websocket-rpcgame.gateWay.gameserver", new WebSocketServerProtocolHandler("/ws"));
            }
        });
        ChannelFuture future = serverBootstrap.bind(9527).sync();
        future.addListener(fl -> {
            if (fl.isSuccess()) {
                serverChannel = future.channel();
                LOGGER.info("Netty server start");
            }
        });
    }

    @Override
    public void destroy() {
        if (serverChannel != null) {
            serverChannel.close();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}