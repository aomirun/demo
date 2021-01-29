package com.example.tarsmqserver.service.mqserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("producer")
@EnableJms
public class Producer {
    /**
     * 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 发送消息，destinationName 是IBM MQ 发送队列的名称，message是待发送的消息
     *
     * @param destinationName
     * @param message
     */
    public boolean sendMessage(String destinationName, final String message) {
        boolean ok = false;
        try {
            jmsTemplate.convertAndSend(destinationName, message);
            ok = true;
        } catch (JmsException ex) {
            ex.printStackTrace();
            ok = false;
        }
        return ok;
    }
}