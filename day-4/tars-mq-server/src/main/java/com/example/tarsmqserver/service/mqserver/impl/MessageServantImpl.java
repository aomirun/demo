package com.example.tarsmqserver.service.mqserver.impl;

import com.example.tarsmqserver.domain.JmqConfig;
import com.example.tarsmqserver.domain.RSAConfig;
import com.example.tarsmqserver.service.mqserver.MessageServant;
import com.example.tarsmqserver.service.mqserver.Producer;
import com.example.tarsmqserver.utils.RSAUtils;
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

    @Autowired
    private RSAConfig rsaConfig;

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
    public boolean encode(String msg, Holder<String> enStr) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        String encodeStr = new String();
        try {
            encodeStr = RSAUtils.encryptedDataOnJavaByPrivateKey(msg, rsaConfig.getPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
            MSG_LOGGER.error(e.getMessage());
            return false;
        }

        enStr.setValue(encodeStr);
        MSG_LOGGER.info("encode: {} -> {}", msg, enStr.value);
        return true;
    }


    @Override
    public boolean encodeWithSend(String msg, String sign) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        Holder<String> enStr = new Holder<String>();
        boolean ok = encode(msg, enStr);
        if (!ok) {
            return false;
        }
        return send(enStr.value);
    }

}