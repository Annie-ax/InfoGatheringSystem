package com.hbut.info.igsServer.config;

/**
 * Created by Administrator on 2016/4/12.
 */
public class NettyConfig extends BaseConfig{
    public static Integer NETTY_LISTEN_PORT() {
        return conf.getInt("NETTY_LISTEN_PORT");
    }
    public static Integer NETTY_BACKLOG() {
        return conf.getInt("NETTY_BACKLOG");
    }
}
