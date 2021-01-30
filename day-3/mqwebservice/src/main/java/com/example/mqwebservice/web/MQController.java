package com.example.mqwebservice.web;

import com.example.mqwebservice.service.mqserver.MessagePrx;
import com.example.mqwebservice.service.mqserver.MessagePrxCallback;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsClient;
import com.qq.tars.spring.annotation.TarsHttpService;
import com.qq.tars.support.log.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

/**
 * @author aomi.run
 */
@TarsHttpService("mqwebserviceObj")
@RestController()
@RequestMapping("/api/mq")
public class MQController {

    private final static Logger RPC_LOGGER = LoggerFactory.getLogger("rpc");

    @TarsClient("example.mqserver.messageObj")
    MessagePrx messagePrx;

    /**
     * 远程调用rpc服务，发送消息给ibmmq
     * 
     * @param msg
     * @return
     */
    @GetMapping("/send/{msg}")
    public boolean rpcSend(@PathVariable("msg") String msg) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }

        // 异步调用
        RPC_LOGGER.info("开始异步发送的报文为: {}", msg);
        messagePrx.async_send(new MessagePrxCallback() {
            @Override
            public void callback_expired() {
            }

            @Override
            public void callback_exception(Throwable ex) {
            }

            @Override
            public void callback_send(boolean ret) {
                RPC_LOGGER.info("接收到异步返回状态: {}, ",ret);
            }

            @Override
            public void callback_encode(boolean ret, String enStr){}

            @Override
            public void callback_encodeWithSend(boolean ret){}
        }, msg);
        
        // 单向调用 所谓单向调用，表示客户端只管发送数据，而不接收服务端的响应，也不管服务端是否接收到请求。
        messagePrx.async_send(null, msg);
        RPC_LOGGER.info("单向调用发送的报文为: {}", msg);


        // 同步调用
        boolean isSend = messagePrx.send(msg);
        RPC_LOGGER.info("同步发送的报文为: {}, 状态： {}", msg, isSend);
        return isSend;
    }

    @GetMapping("/encode/{sign}")
    public String rpcEncode(@PathVariable("sign") String sign) {
        Holder<String> enStr = new Holder<String>();
        messagePrx.encode(sign, enStr);
        String ret = enStr.value;
        RPC_LOGGER.info("收到的密文是: {}", enStr.value);
        return ret;
    }

    @GetMapping("/send/{msg}/encode/{sign}")
    public boolean rpcEncodeWithSend(@PathVariable("msg") String msg, @PathVariable("sign") String sign) {
        if (StringUtils.isBlank(msg) || StringUtils.isBlank(sign)) {
            return false;
        }
        boolean ok = messagePrx.encodeWithSend(msg, sign);
        if (!ok) {
            return false;
        }
        RPC_LOGGER.info("发送的报文为: {}", msg);
        return true;
    }
}