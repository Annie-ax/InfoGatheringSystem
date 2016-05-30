package com.hbut.info.igsServer.netty;

import com.hbut.info.igsServer.config.NettyConfig;
import com.hbut.info.igsServer.rabbitmq.QueueConsumer;
import com.hbut.info.igsServer.rabbitmq.RabbitMqFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/4/13.
 */
@Component
public class ServerBootstrap implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(ServerBootstrap.class);

    @Autowired
    private NettyServer nettyServer;
    @Autowired
    private QueueConsumer queueConsumer;
    @Autowired
    private RabbitMqFactory rabbitMqFactory;
    public void initServer(){
        System.out.println("开始启动服务");
        //启动netty
        nettyServer.bind(NettyConfig.NETTY_LISTEN_PORT());
        //启动rabbitMq消费者
        System.out.println("netty绑定监听端口" + NettyConfig.NETTY_LISTEN_PORT() + "完成");
        queueConsumer.listen();
        System.out.println("rabbitMq消费者启动完成");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initServer();

    }
}
