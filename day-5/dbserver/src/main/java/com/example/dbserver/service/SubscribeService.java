package com.example.dbserver.service;

import com.example.dbserver.NsqConfig;
import com.sproutsocial.nsq.Message;
import com.sproutsocial.nsq.Subscriber;

import org.springframework.beans.factory.annotation.Autowired;

public class SubscribeService {
    @Autowired
    NsqConfig nsqConfig;

    
    public void init(){
        System.out.println("nsq sub init...");
    }

    public void destroy(){
        System.out.println("nsq sub destroy...");
    }

    public void SubscribeService() {
        Subscriber subscriber = new Subscriber(nsqConfig.getNsqlookupdHost());
        MsgHandler handler = new MsgHandler();
        subscriber.subscribe("example_topic", "test_channel", handler);
    }

    public static void handleData(byte[] data) {
        System.out.println("Received:" + new String(data));
    }

    public static void handleMessage(Message msg) {
        try {
            byte[] data = msg.getData();
            // ... complicated work ...
            msg.finish();
        }
        catch (Exception e) {
            msg.requeue();
        }
    }
}
