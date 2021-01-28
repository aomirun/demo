package com.example.client.utils;

public class Resp {
    /**
     * 请求成功返回
     * 
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result msg = new Result();
        msg.setCode(200);
        msg.setMsg("请求成功");
        msg.setData(object);
        return msg;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String resultmsg) {
        Result msg = new Result();
        msg.setCode(code);
        msg.setMsg(resultmsg);
        return msg;
    }

}