package com.example.dbserver;

public interface MessageService {
    /**
     * 从nsq的topic获取到数据保存到db中存储
     */
    void save(MessageModel msg);
    void saveData(MessageData msg);
    void listen();
}
