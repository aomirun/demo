package com.example.tarsmqserver.service.mqserver.impl;

import com.example.tarsmqserver.service.mqserver.MessageServant;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;

/**
 * @author aomi.run
 */
@TarsServant("messageObj") 
public class MessageServantImpl implements MessageServant {
    @Override
    public boolean send(String msg) {
        // TODO 先简单走流程 后面实现具体与ibmmq的对接
        if (msg == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean encode(String sign, Holder<String> enStr) {
        // TODO 先简单走流程 后面实现具体与ibmmq的对接
        if (sign == null) {
            return false;
        }
        enStr.setValue("123" + sign + "456");
        return true;
    }

    @Override
    public boolean encodeWithSend(String msg, String sign) {
        // TODO 先简单走流程 后面实现具体与ibmmq的对接
        if (sign == null || msg == null) {
            return false;
        }

        Holder<String> enStr = new Holder<String>();
        boolean ok = encode(sign, enStr);
        if (!ok) {
            return false;
        }

        return send(enStr + msg);
    }
}