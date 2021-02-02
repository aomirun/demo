package com.example.dbserver;

import com.example.dbserver.service.SubscribeService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aomi.run
 */
@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public SubscribeService subscribeService(){
        return new SubscribeService();
    }
}
