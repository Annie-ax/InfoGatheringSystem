package com.hbut.info.igsServer.netty;

import com.hbut.info.igsServer.config.NettyConfig;
import com.hbut.info.igsServer.rabbitmq.RabbitMqFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by keaxiang on 2015/12/28.
 */
@Service
public class NettyServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Autowired
    RabbitMqFactory rabbitMqFactory;
    @Autowired
    ServerHandler serverHandler;

    public void bind(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(serverHandler);
                    }
                }).option(ChannelOption.SO_BACKLOG, NettyConfig.NETTY_BACKLOG()).childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            //bootstrap.bind(port).syncUninterruptibly();
            bootstrap.bind(port).sync();
            logger.info("netty 监听成功{}",port);
        } catch (Exception e) {
            //System.out.println("开启监听失败：" + e.getMessage());
            logger.error(e.getMessage(),e);
        }
    }

}
