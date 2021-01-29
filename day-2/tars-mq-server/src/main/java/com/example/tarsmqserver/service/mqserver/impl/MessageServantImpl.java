package com.example.tarsmqserver.service.mqserver.impl;

import com.example.tarsmqserver.domain.JmqConfig;
import com.example.tarsmqserver.service.mqserver.MessageServant;
import com.example.tarsmqserver.service.mqserver.Producer;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 发送消息到IBM MQ
     */
    @Override
    public boolean send(final String msg) {
        if (msg.length() == 0) {
            return false;
        }

        final String queueName = jmqConfig.getSendQueueName();
        return producer.sendMessage(queueName, msg);
    }

    @Override
    public boolean encode(final String sign, final Holder<String> enStr) {
        // TODO 先简单走流程 后面实现具体与ibmmq的对接
        if (sign.length() == 0) {
            return false;
        }
        enStr.setValue("123" + sign + "456");
        return true;
    }

    @Override
    public boolean encodeWithSend(final String msg, final String sign) {
        if (sign.length() == 0 || msg.length() == 0) {
            return false;
        }

        final Holder<String> enStr = new Holder<String>();
        final boolean ok = encode(sign, enStr);
        if (!ok) {
            return false;
        }

        return send(enStr + msg);
    }
}