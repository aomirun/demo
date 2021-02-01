package com.example.tarsmqserver.service.mqserver.impl;

import com.example.tarsmqserver.domain.JmqConfig;
import com.example.tarsmqserver.service.mqserver.MessageServant;
import com.example.tarsmqserver.service.mqserver.Producer;
import com.example.tarsmqserver.service.rsaserver.CipherPrx;
import com.example.tarsmqserver.service.rsaserver.SignType;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsClient;
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

    @TarsClient("example.rsaserver.cipherObj")
    CipherPrx cipherPrx;

    private final static Logger MQSERVER_LOGGER = LoggerFactory.getLogger("MQSERVER");

    /**
     * 发送消息到IBM MQ
     */
    @Override
    public boolean send(String msg) {

        if (StringUtils.isBlank(msg)) {
            return false;
        }

        try {
            String queueName = jmqConfig.getSendQueueName();
            MQSERVER_LOGGER.info("send: {} -> {}", queueName, msg);
            return producer.sendMessage(queueName, msg);
                
        } catch (Exception e) {
            return exHandler("send", e);
        }
    }

    @Override
    public boolean encode(String msg, Holder<String> enStr) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        try {
            boolean ok = cipherPrx.encodeByPrivateKey(msg, enStr);
            MQSERVER_LOGGER.info("encode: {} -> {}", msg, enStr.value);
            return ok;
        } catch (Exception e) {
            return exHandler("encode", e);
        }

    }

    @Override
    public boolean encodeWithSend(String msg) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        try {
            Holder<String> enStr = new Holder<String>();
            boolean ok = encode(msg, enStr);
            if (!ok) {
                return false;
            }
            return send(enStr.value);

        } catch (Exception e) {
            return exHandler("encodeWithSend", e);
        }
    }

    @Override
    public boolean signWithSend(String msg, boolean isEncode) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        try {
            // 原内容去生成签名
            Holder<String> signStr = new Holder<String>();
            boolean ok = cipherPrx.sign(msg, SignType.MD5withRSA.value(), signStr);
            MQSERVER_LOGGER.info("signWithSend: {} -> {}", msg, signStr.value);
            if (!ok) {
                return false;
            }
            String content = signStr.getValue() + "|";

            // 是否需要对发送的内容进行加密
            if (isEncode) {
                Holder<String> enStr = new Holder<String>();

                ok = encode(msg, enStr);
                // 加密失败处理逻辑,根据业务需求不同,可以采用返回失败或不加密,直接发送明文
                if (!ok) {
                    // 这里选择直接发送明文
                    content += msg;
                } else {
                    // 加密成功,发送密文
                    content += enStr.getValue();
                }

            } else {
                // 不加密,发送明文
                content += msg;
            }
            return send(content);
        } catch (Exception e) {
            return exHandler("signWithSend", e);
        }
    }

    /**
     * 错误处理器 类里面统一一下,有空再研究一下统一错误机制
     * @param funcname
     * @param e
     * @return
     */
    private boolean exHandler(String funcname, Exception e) {
        e.printStackTrace();
        MQSERVER_LOGGER.error("{}() fail call. cause: {}", funcname, e.toString());
        return false;

    }

}