package com.example.tarsmqserver.service.mqserver;

import com.example.tarsmqserver.domain.JmqConfig;
import com.qq.tars.support.log.LoggerFactory;
import com.qq.tars.support.notify.NotifyHelper;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;






/**
 * @author aomi.run
 */ 
@Component
public class ConsumerListener extends MessageListenerAdapter {
    // private static Logger logger = LoggerFactory.getLogger(ConsumerListener.class);
    private final static Logger FLOW_LOGGER = LoggerFactory.getLogger("flow");

    // private final static Logger FLOW_LOGGER = LoggerFactory.getLogger("flow"); 
    /**
     * 使用JmsListener配置消费者监听的队列
     * 
     * @param receivedMsg 接收到的消息
     */
    @JmsListener(destination = "#{@conf.recvQueueName}")
    public void receiveQueue(String receivedMsg) {
        FLOW_LOGGER.info("Consumer收到的报文为: {}", receivedMsg);
        NotifyHelper.getInstance().notifyNormal(receivedMsg);
    }

    @Bean
    public JmqConfig conf() {
        return new JmqConfig();
    }
}