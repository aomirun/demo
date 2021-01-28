package com.example.client.domain;

import javax.validation.constraints.NotBlank;

/**
 * @author aomi.run
 */
public class RPCMessage {
    @NotBlank(message = "请输入消息")
    private String msg;

    @NotBlank(message = "请输入签名要素")
    private String sign;

    public String getMsg() {
        return msg;
    }

    public String getSign() {
        return sign;
    }
}