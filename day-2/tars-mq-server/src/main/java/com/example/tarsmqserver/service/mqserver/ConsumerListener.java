package com.example.tarsmqserver.service.mqserver;

import com.example.tarsmqserver.domain.JmqConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener extends MessageListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    /**
     * 使用JmsListener配置消费者监听的队列
     * 
     * @param receivedMsg 接收到的消息
     */
    // @JmsListener(destination = "#{@conf.recvQueueName}")
    @JmsListener(destination = "DEV.QUEUE.1")
    public void receiveQueue(String receivedMsg) {
        logger.info("Consumer收到的报文为: {}", receivedMsg);
    }

    // @Bean
    // public JmqConfig conf() {
    //     return new JmqConfig();
    // }
}