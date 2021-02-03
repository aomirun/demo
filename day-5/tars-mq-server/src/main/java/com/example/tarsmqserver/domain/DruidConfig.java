package com.example.tarsmqserver.domain;

import java.util.HashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aomi.run
 */
@Configuration
public class DruidConfig {

    /**
     * 数据源
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    //后台监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要账号密码
       HashMap<String,String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin");//loginUsername固定
        initParameters.put("loginPassword","admin");//loginPassword固定

        //允许访问者
        initParameters.put("allow","");

        //禁止访问者 initParameters.put("jack","192.168.132.115");


        bean.setInitParameters(initParameters);//初始化
        return bean;
    }
}
