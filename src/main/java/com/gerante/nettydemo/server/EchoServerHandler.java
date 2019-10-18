/**
 * 1.@Sharable 标识这类的实例之间可以在 channel 里面共享
 * 2.日志消息输出到控制台
 * 3.将所接收的消息返回给发送者。注意，这还没有冲刷数据
 * 4.冲刷所有待审消息到远程节点。关闭通道后，操作完成
 * 5.打印异常堆栈跟踪
 * 6.关闭通道
 */
package com.gerante.nettydemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
//1
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        //2
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        //3
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //4
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
            .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //5
        cause.printStackTrace();
        //6
        ctx.close();
    }
}