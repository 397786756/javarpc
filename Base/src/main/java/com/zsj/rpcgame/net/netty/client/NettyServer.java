package com.zsj.rpcgame.net.netty.client;

import com.zsj.rpcgame.log.Log;
import com.zsj.rpcgame.net.netty.SimpleNettyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
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
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("nettyServer")
public class NettyServer  {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static Channel serverChannel;

    public  Channel getServerChannel() {
        return serverChannel;
    }

    public void run(String... args) throws Exception {
        if (args==null){
            bind(9527);
        }else {
            bind(9528);
        }
    }

    private void bind(int port) throws InterruptedException {
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
                channel.pipeline().addLast("handler", new SimpleNettyHandler());
            }
        });
        ChannelFuture future = serverBootstrap.bind(port).sync();
        future.addListener(fl -> {
            if (fl.isSuccess()) {
                serverChannel = future.channel();
                Log.info("Netty server start,port:{}", port);
            }
        });
    }

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