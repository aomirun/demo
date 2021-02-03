package com.example.tarsmqserver;

import com.qq.tars.spring.annotation.EnableTarsServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author aomi.run
 */
@SpringBootApplication
@EnableTarsServer
@MapperScan("com.example.tarsmqserver.dao")
public class TarsMqServerApplication {

	public static void main(String[] args) {
		// 关闭 spring boot 自带的web服务 目前场景只用到了rpc服务
		SpringApplication app = new SpringApplication(TarsMqServerApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

}
