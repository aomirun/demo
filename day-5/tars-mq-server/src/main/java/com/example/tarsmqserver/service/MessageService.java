package com.example.tarsmqserver.service;

import com.example.tarsmqserver.domain.MessageData;
import com.example.tarsmqserver.domain.MessageModel;

public interface MessageService {
    /**
     * 从nsq的topic获取到数据保存到db中存储
     */
    void save(MessageModel msg);
    void saveData(MessageData msg);
}
