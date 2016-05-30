package com.hbut.info.igsServer.config;

/**
 * Created by Administrator on 2016/4/12.
 */
public class QueueConfig extends BaseConfig{
    public static String QUEUE_ADDRESS() {
        return conf.getString("QUEUE_HOST");
    }
    public static Integer QUEUE_PORT() {
        return conf.getInt("QUEUE_PORT");
    }
    public static String QUEUE_USER() {
        return conf.getString("QUEUE_USER");
    }
    public static String QUEUE_PWD() {
        return conf.getString("QUEUE_PWD");
    }
    public static String QUEUE_NAME() {
        return conf.getString("QUEUE_NAME");
    }
}
