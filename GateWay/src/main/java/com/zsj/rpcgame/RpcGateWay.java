package com.zsj.rpcgame;

import com.zsj.rpcgame.net.netty.client.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author songjie.zheng
 * @Description: 服务器启动类
 * @date 2020/9/2220:56
 */
@SpringBootApplication
public class RpcGateWay {
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(RpcGateWay.class);
        // 取消tomcat
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
        NettyServer nettyServer = new NettyServer();
        nettyServer.run(null);

    }
}
