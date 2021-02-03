package com.example.tarsmqserver.dao;

import com.example.tarsmqserver.domain.MessageData;
import com.example.tarsmqserver.domain.MessageModel;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * 消息存储接口 UserMapper
 * 
 * @author aomi.run
 */

@Repository
public interface MessageMapper {
    /**
     * 从nsq的topic获取到数据保存到db中存储
     * @param msg
     */
    @Insert("insert into message (content,created) values (#{content},#{created})")
    void save(MessageModel msg);

    /**
     * 从nsq的topic获取到数据保存到db中存储       测试@data写法数据的提交
     * @param msg
     */
    @Insert("insert into message (content,created) values (#{content},#{created})")
    void saveData(MessageData msg);
}
