package com.example.tarsmqserver.domain;

import lombok.Data;

/**
 * 消息实体的另一种写法测试
 * 
 * @author aomi.run
 */
@Data
public class MessageData {
    /**
     * 消息ID
     */
    private long msgid;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息保存到数据库的时间
     */
    private long created;

}
