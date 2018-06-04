package com.alleyz.netty.chapter01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * desc:
 *      1、 监听和接收连接进来的请求
 *      2、 配置Channel来通知一个关于入站消息的ChannelHandler实例
 * date: 2018-05-25
 *
 * @author zhaihw
 */
public class EchoServer {
    private final int port;

    private EchoServer (int port) {
        this.port = port;
    }

    private void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) //指定使用NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) // 设置socket地址与所选端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 添加ChannelHandler到Channel的Pipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new EchoServerHandler());

                        }
                    });
            ChannelFuture future = bootstrap
                    .bind() // 绑定服务器
                    .sync(); // 等待服务器关闭
            System.out.println("EchoServer is started and listener on " + future.channel().localAddress());
            future.channel()
                    .closeFuture() // 当被通知关闭时
                    .sync(); // 等待关闭
        }finally {
            group.shutdownGracefully().sync(); // 关闭服务 释放资源
        }
    }

    public static void main(String[] args) throws Exception{
        new EchoServer(13001).start();
    }
}
