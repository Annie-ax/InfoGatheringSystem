package com.hbut.info.igsServer.netty;

import com.hbut.info.igsServer.rabbitmq.Producer;
import com.hbut.info.igsServer.rabbitmq.RabbitMqFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by keaxiang on 2015/12/28.
 */
@Component
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private Producer producer;
    @Autowired
    private RabbitMqFactory rabbitMqFactory;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.toString() + " 连接进来了");
        /*//创建一个生产者，即创建一个队列，只创建一次
        if (producer == null){
            producer = new Producer("myQueue");
        }*/
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println(ctx.toString() + " 退出了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //获取客户端发来的数据
        System.out.println("客户端发来了数据！");
        String receiveStr = (String)msg;
        System.out.println("netty receive:" + receiveStr);
        System.out.println("send into mq");
        try {
            //写到消息队列中去
            rabbitMqFactory.send(receiveStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("有人意外退出了");
        ctx.close();
    }
}
