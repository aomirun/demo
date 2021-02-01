package com.example.tarsmqserver.service.mqserver;

import javax.jms.ConnectionFactory;

import com.example.tarsmqserver.domain.JmqConfig;
import com.qq.tars.support.log.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import ch.qos.logback.classic.Logger;

/**
 * @author aomi.run
 */
@Component
public class ConsumerListener {
    private final static Logger JMS_LISTENER = LoggerFactory.getLogger("JMS_LISTENER");

    /**
     * 使用JmsListener配置消费者监听的队列
     * 
     * @param receivedMsg 接收到的消息
     */

    // @JmsListener(destination = "DEV.QUEUE.1")
    @JmsListener(destination = "#{@conf.recvQueueName}")
    public void receiveQueue(String receivedMsg) {
        JMS_LISTENER.info("RECEIVE: {}", receivedMsg);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
            ExampleErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    @Service
    public class ExampleErrorHandler implements ErrorHandler {
        @Override
        public void handleError(Throwable t) {
            // handle exception here
            JMS_LISTENER.error(t.toString());
        }
    }

    @Bean
    public JmqConfig conf() {
        return new JmqConfig();
    }
}