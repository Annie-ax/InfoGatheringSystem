package com.hbut.info.igsServer.config;



/**
 * Created by Administrator on 2016/4/12.
 */
public class RedissionConfig extends BaseConfig{
    public static String REDIS_ADDRESS() {
        return conf.getString("redis.ip");
    }
    public static Integer REDIS_PORT() {
        return conf.getInt("redis.port");
    }
}
