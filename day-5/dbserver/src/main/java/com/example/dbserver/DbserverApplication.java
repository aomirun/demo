package com.example.dbserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dbserver.dao")
public class DbserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbserverApplication.class, args);
	}

}
