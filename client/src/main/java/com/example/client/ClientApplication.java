package com.example.client;

import com.qq.tars.spring.annotation.EnableTarsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author aomi.run
 */
@SpringBootApplication
@EnableTarsServer
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
		// 关闭web服务
		// SpringApplication app = new SpringApplication(ClientApplication.class);
		// app.setWebApplicationType(WebApplicationType.NONE);
		// app.run(args);
	}

}
