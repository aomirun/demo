package com.example.mqwebservice;

import com.example.mqwebservice.common.EnableGlobalResponse;
import com.qq.tars.spring.annotation.EnableTarsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTarsServer
@EnableGlobalResponse
public class MqwebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqwebserviceApplication.class, args);
	}

}
