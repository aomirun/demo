package com.example.client.controller;

import javax.validation.Valid;

import com.example.client.domain.RPCMessage;
import com.example.client.service.demo.MessagePrx;
import com.example.client.utils.Result;
import com.example.client.utils.Resp;
import com.qq.tars.spring.annotation.TarsClient;
import com.qq.tars.spring.annotation.TarsHttpService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aomi.run
 */
@RestController
@TarsHttpService("restMsgObj")
@RequestMapping("/api/mq")
public class MessageController {
    @TarsClient("example.demo.MsgObj")
    MessagePrx messagePrx;

    final static String ERR_REQUEST = "错误的请求";
    final static String ERR_NETWORK = "网络请求错误，请重试";

    @GetMapping("/send/{msg}")
    public Result rpcSend(@PathVariable("msg") String msg) {
        if (msg == null) {
            return Resp.error(400, ERR_REQUEST);
        }

        boolean ok = false;
        try {
            ok = messagePrx.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ok) {
            return Resp.success();
        }
        return Resp.error(400, ERR_NETWORK);
    }

    @GetMapping("/encode/{sign}")
    public Result rpcEncode(@PathVariable("sign") String sign) {
        if (sign == null) {
            return Resp.error(400, ERR_REQUEST);
        }

        String enStr = null;
        boolean isErr = false;
        try {
            enStr = messagePrx.encode(sign);
            if (enStr != null) {
            }
        } catch (Exception e) {
            isErr = true;
            e.printStackTrace();
        }

        if (isErr) {
            return Resp.error(400, ERR_NETWORK);
        }

        if (enStr == null) {
            return Resp.error(400, ERR_REQUEST);
        }
        return Resp.success(enStr);
    }

    @PostMapping("")
    public Result rpcEncodeWithSend(@Valid @RequestBody RPCMessage body) {
        boolean ok = false;
        try {
            ok = messagePrx.encodeWithSend(body.getMsg(), body.getSign());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ok) {
            return Resp.success();
        }
        return Resp.error(400, ERR_NETWORK);
    }
}