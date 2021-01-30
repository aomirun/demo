package com.example.tarsmqserver.service.mqserver.impl;

import com.example.tarsmqserver.domain.JmqConfig;
import com.example.tarsmqserver.service.mqserver.MessageServant;
import com.example.tarsmqserver.service.mqserver.Producer;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import com.qq.tars.support.log.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

/**
 * @author aomi.run
 */
@TarsServant("messageObj")
@Component
public class MessageServantImpl implements MessageServant {
    @Autowired
    private Producer producer;

    @Autowired
    private JmqConfig jmqConfig;

    private final static Logger MSG_LOGGER = LoggerFactory.getLogger("msg");

    /**
     * 发送消息到IBM MQ
     */
    @Override
    public boolean send(String msg) {
        
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        String queueName = jmqConfig.getSendQueueName();
        MSG_LOGGER.info("send: {} -> {}", queueName, msg);
        return producer.sendMessage(queueName, msg);
    }

    @Override
    public boolean encode(String sign, Holder<String> enStr) {
        // TODO 先简单走流程 后面实现具体加密签名之类的
        if (StringUtils.isBlank(sign)) {
            return false;
        }
        enStr.setValue("123" + sign + "456");
        MSG_LOGGER.info("encode: {} -> {}", sign, enStr.value);
        return true;
    }

    @Override
    public boolean encodeWithSend(String msg, String sign) {
        if (StringUtils.isBlank(msg) || StringUtils.isBlank(sign)) {
            return false;
        }

        Holder<String> enStr = new Holder<String>();
        boolean ok = encode(sign, enStr);
        if (!ok) {
            return false;
        }
        return send(enStr.value + msg);
    }
}