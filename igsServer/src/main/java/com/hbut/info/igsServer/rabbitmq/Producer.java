package com.hbut.info.igsServer.rabbitmq;

import com.hbut.info.igsServer.config.QueueConfig;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by admin on 2015/12/17.
 */
@Service
public class Producer {
    @Autowired
    RabbitMqFactory rabbitMqFactory;

    public void sendObject(Serializable object) throws IOException {
        Channel channel = null;
        channel = rabbitMqFactory.getChannel();
        String queueName = QueueConfig.QUEUE_NAME();
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }
}
