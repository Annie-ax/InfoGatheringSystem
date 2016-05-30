package com.hbut.info.igsServer.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by keaxiang on 2015/12/28.
 */
public class Client {
    private String host;
    private int port;
    private ChannelFuture future;
    private Bootstrap bootstrap;
    private EventLoopGroup workerGroup;
    ChannelHandlerContext channelHandlerContext;

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    /**
     * 带参构造函数
     * @param host 远程服务端的ip地址
     * @param port 远程服务端开启的监听端口
     */
    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    /**
     *客户端的初始化方法，该方法成功执行后，客户端会注册一个handler处理与服务端的通信
     */
    public void init(){
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("连接到服务器");
                        setChannelHandlerContext(ctx);
                    }
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        System.out.println("连接发生异常");
                        future.channel().closeFuture();
                        future = null;
                        ctx.close();
                        closeChannel();
                    }
                });
            }
        });
    }

    /**
     *连接服务器，当连接发生异常时，抛出InterruptedException
     */
    public ChannelFuture conn(){
        init();
        // 发起连接
        try {
            future = bootstrap.connect(host, port).sync();
        } catch (Exception e) {
            System.out.println("连接失败");
        }
        return future;
    }

    /**
     * 关闭连接
     */
    public void closeChannel(){
        workerGroup.shutdownGracefully();
    }

    /**
     * 判断是否连接上
     * @return 如果与服务处于连接状态，则返回true，否则返回false
     */
    public Boolean isConnection(){
        if(future != null){
            return true;
        }
        return false;
    }

    /**
     *向服务端发送消息
     * @param msg 要求格式的字符串
     */
    public void sendMsg(String msg){
        ChannelHandlerContext ctx = getChannelHandlerContext();
        if(ctx != null){
            msg = msg + System.getProperty("line.separator");
            ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
            encoded.writeBytes(msg.getBytes());
            ctx.writeAndFlush(encoded);
        }
    }
}
