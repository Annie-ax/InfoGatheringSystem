package com.hbut.info.igsServer.netty;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/4/13.
 */
public class ServerClient {
    public static void main(String[] args) throws ConfigurationException {
        new ClassPathXmlApplicationContext("bootstrap.xml");
        System.out.println("服务端启动完成");
    }
}
