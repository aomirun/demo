package com.example.dbserver;


/**
 * 消息实体类
 * 
 * @author aomi.run
 */
public class MessageModel {
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public String getContent() {
        return content;
    }

    public long getCreated() {
        return created;
    }

    public long getMsgid() {
        return msgid;
    }

}
