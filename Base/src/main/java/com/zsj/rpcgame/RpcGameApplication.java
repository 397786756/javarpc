package com.zsj.rpcgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author songjie.zheng
 * @Description: ${todo}
 * @date 2020/9/2220:56
 */
@SpringBootApplication
public class RpcGameApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RpcGameApplication.class);
		// 取消tomcat
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}
}
