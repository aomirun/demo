package com.example.dbserver;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import com.alibaba.druid.pool.DruidDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class DbserverApplicationTests {

    @Resource
    private DruidDataSource dataSource;

    @Test
    public void testDataSourceExists() throws Exception {
        assertThat(dataSource).isNotNull();
    }

    @Test
    public void testDataSourcePropertiesOverridden() throws Exception {
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:mysql://127.0.0.1:3307/db1");
        assertThat(dataSource.getInitialSize()).isEqualTo(2);
        assertThat(dataSource.getMaxActive()).isEqualTo(30);
        assertThat(dataSource.getMinIdle()).isEqualTo(2);
        assertThat(dataSource.getMaxWait()).isEqualTo(1234);
        assertThat(dataSource.isPoolPreparedStatements()).isTrue();
        // assertThat(dataSource.getMaxOpenPreparedStatements()).isEqualTo(5);
        // //Duplicated with following
        assertThat(dataSource.getMaxPoolPreparedStatementPerConnectionSize()).isEqualTo(5);
        assertThat(dataSource.getValidationQuery()).isEqualTo("select 1");
        assertThat(dataSource.getValidationQueryTimeout()).isEqualTo(1);
        assertThat(dataSource.isTestWhileIdle()).isTrue();
        assertThat(dataSource.isTestOnBorrow()).isTrue();
        assertThat(dataSource.isTestOnReturn()).isTrue();
        assertThat(dataSource.getTimeBetweenEvictionRunsMillis()).isEqualTo(10000);
        assertThat(dataSource.getMinEvictableIdleTimeMillis()).isEqualTo(30001);
        assertThat(dataSource.isAsyncCloseConnectionEnable()).isEqualTo(true);
    }

    @Autowired
    MessageServiceImpl imp;

    @Test
    public void saveMessage() {
        MessageModel message = new MessageModel();
        message.setContent("abcdefg");
        message.setCreated(System.currentTimeMillis());
        imp.save(message);

        MessageData messageData = new MessageData();
        messageData.setContent("abcdefg");
        messageData.setCreated(System.currentTimeMillis());
        imp.saveData(messageData);
    }

    @Autowired
    NsqConfig nsqConfig;

    @Test
    public void NsqConfig() {
        assertThat(nsqConfig.getNode()).isEqualTo("172.25.0.213:4150");
        assertThat(nsqConfig.getNsqlookupdHost()).isEqualTo("172.25.0.211:4160");
    }

    @Test
    public void subscribe() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getAliases("subscribeService");
    }

}
