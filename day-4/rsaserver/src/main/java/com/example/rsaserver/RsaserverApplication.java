package com.example.rsaserver;

import com.qq.tars.spring.annotation.EnableTarsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTarsServer
public class RsaserverApplication {

	public static void main(String[] args) {
		// SpringApplication.run(RsaserverApplication.class, args);
		// 关闭 spring boot 自带的web服务 目前场景只用到了rpc服务
		SpringApplication app = new SpringApplication(RsaserverApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

}
