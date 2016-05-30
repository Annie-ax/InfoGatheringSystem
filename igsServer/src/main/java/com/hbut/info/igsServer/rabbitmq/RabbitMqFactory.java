package com.hbut.info.igsServer.rabbitmq;

import com.hbut.info.igsServer.config.QueueConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by admin on 2015/12/17.
 */
@Service
public class RabbitMqFactory implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqFactory.class);
    //private Channel channel;
    //private Connection connection;
    public Connection getConnection()  {
        // 创建一个连接工厂
        Connection connection = null;
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(QueueConfig.QUEUE_ADDRESS());
        factory.setPort(QueueConfig.QUEUE_PORT());
        factory.setUsername(QueueConfig.QUEUE_USER());
        factory.setPassword(QueueConfig.QUEUE_PWD());
        // 获取一个连接
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
        //channel = connection.createChannel();
        //channel.queueDeclare(queueName, false, false, false, null);
    }

    public Channel getChannel(){
        Connection connection = getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }
    public void send(String msg){
        Channel channel = null;
        channel = getChannel();
        String queueName = QueueConfig.QUEUE_NAME();
        try {
            channel.basicPublish("", queueName, null, SerializationUtils.serialize(msg));
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Channel channel = getChannel();
        channel.queueDeclare(QueueConfig.QUEUE_NAME(),false,false,false,null);
    }
}
