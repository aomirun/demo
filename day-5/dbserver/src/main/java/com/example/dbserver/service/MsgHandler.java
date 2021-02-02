package com.example.dbserver.service;

import com.example.dbserver.MessageModel;
import com.example.dbserver.MessageServiceImpl;
import com.sproutsocial.nsq.Message;
import com.sproutsocial.nsq.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 消息处理器
 * @author aomi.run
 */
public class MsgHandler implements MessageHandler {
    @Autowired
    MessageServiceImpl imp;

    @Override
    public void accept(Message msg) {
        // TODO Auto-generated method stub
        try {
            byte[] data = msg.getData();
            // ... complicated work ...
            MessageModel msgObject = new MessageModel();
            msgObject.setContent(data.toString());
            msgObject.setCreated(System.currentTimeMillis());
            imp.save(msgObject);

            msg.finish();
        }
        catch (Exception e) {
            msg.requeue();
        }
    }
}
