package com.example.demo.impl;

import com.example.demo.MessageServant;
import com.example.demo.jms.JmqConfig;
import com.example.demo.jms.Producer;
import com.qq.tars.spring.annotation.TarsServant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author aomi.run
 */
@Component
@TarsServant("MsgObj")
public class MessageServantImpl implements MessageServant {

    @Autowired
    private Producer producer;

    @Autowired
    private JmqConfig jmqConfig;

    /**
     * 发送消息到IBM MQ
     */
    @Override
    public boolean send(String message) {
        String destination = jmqConfig.getSendQueueName();
        return producer.sendMessage(destination, message);
    }

    /**
     * 对plain进行加密，返回加密后的内容
     */
    @Override
    public String encode(String plain) {
        if (plain.length() == 0) {
            return null;
        }
        String encodeStr = "1231#!@#!";
        return encodeStr;

    }

    /**
     * 对plain进行加密，并同msg一起发送到IBM MQ
     */
    @Override
    public boolean encodeWithSend(String msg, String plain) {
        if (msg.length() == 0 || plain.length() == 0) {
            return false;
        }

        String hashStr = this.encode(plain);
        boolean ok = this.send(hashStr + msg);
        return ok;
    }
}