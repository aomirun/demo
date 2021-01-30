package com.example.mqwebservice.common;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 全局统一返回注入spring容器(将需要忽略的包或类从配置中获取)
 *
 * @author Tom
 * @date 2020-12-18
 */
@Configuration
@EnableConfigurationProperties(GlobalDefaultProperties.class)
@PropertySource(value = "classpath:config/dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfig {

    @Bean
    public GlobalResponseHandler commonResponseDataAdvice(GlobalDefaultProperties globalDefaultProperties) {
        return new GlobalResponseHandler(globalDefaultProperties);
    }
}
