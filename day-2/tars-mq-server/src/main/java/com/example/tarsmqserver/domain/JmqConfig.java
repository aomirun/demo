package com.example.tarsmqserver.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JmqConfig {

    @Value("${ibm.mq.sendQueueName}")
    private String sendQueueName;

    @Value("${ibm.mq.recvQueueName}")
    private  String recvQueueName;


    public String getSendQueueName() {
        return sendQueueName;
    }

    public String getRecvQueueName(){
        return recvQueueName;
    }

}