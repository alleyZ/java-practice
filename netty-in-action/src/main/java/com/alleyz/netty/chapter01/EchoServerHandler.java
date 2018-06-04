package com.alleyz.netty.chapter01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * desc:
 *      1、 ChannelHandler是给不同类型的事件调用
 *      2、 应用程序实现或扩展ChannelHandler挂接到事件生命周期和提供自定义应用逻辑
 * date: 2018-05-25
 *
 * @author zhaihw
 */
@ChannelHandler.Sharable  // 类的实例之间在channel中可以共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("Handler: channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("Handler: channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Handler: channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("Handler: channelInactive");
    }


    // 每个消息入站都会调用此方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println("Handler: channelRead");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("================");
        System.out.println("Received: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("================");
        // 将接受到的消息返还给发送者， 此处并未冲刷数据
        ctx.write(buf);
    }

    // 通知处理器最后的 channelread() 是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("Handler: channelReadComplete");
        // 冲刷所有待审消息到远程端节点 关闭通道后，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        System.out.println("Handler: userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        System.out.println("Handler: channelWritabilityChanged");
    }

    // 读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("Handler: exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
