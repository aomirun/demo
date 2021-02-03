package com.example.tarsmqserver.service;


import com.example.tarsmqserver.dao.MessageMapper;
import com.example.tarsmqserver.domain.MessageData;
import com.example.tarsmqserver.domain.MessageModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息接口的实现
 * 
 * @author aomi.run
 */
@Component
public class MessageServiceImpl implements MessageService { 

    @Autowired
    MessageMapper messageMapper;

    @Override
    public void save(MessageModel msg) {
        // TODO Auto-generated method stub
        messageMapper.save(msg);
    }

    @Override
    public void saveData(MessageData msg) {
        // TODO Auto-generated method stub
        messageMapper.saveData(msg);
    }

}
