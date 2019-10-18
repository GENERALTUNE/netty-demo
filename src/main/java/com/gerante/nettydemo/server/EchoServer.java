/**
 * 1.设置端口值（抛出一个 NumberFormatException 如果该端口参数的格式不正确）
 * 2.呼叫服务器的 start() 方法
 * 3.创建 EventLoopGroup
 * 4.创建 ServerBootstrap
 * 5.指定使用 NIO 的传输 Channel
 * 6.设置 socket 地址使用所选的端口
 * 7.添加 EchoServerHandler 到 Channel 的 ChannelPipeline
 * 8.绑定的服务器;sync 等待服务器关闭
 *
 * 9.关闭 channel 和 块，直到它被关闭
 * 10.关闭 EventLoopGroup，释放所有资源。
 */

package com.gerante.nettydemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println(
//                    "Usage: " + EchoServer.class.getSimpleName() +  " <port>");
//            return;
//        }
        //1
//        int port = Integer.parseInt(args[0]);
        int port = 8080;
        //2
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        //3
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //4
            ServerBootstrap b = new ServerBootstrap();
            //5
            b.group(group).channel(NioServerSocketChannel.class)
                    //6
                        .localAddress(new InetSocketAddress(port))
                    //7
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch)
                                    throws Exception {
                                ch.pipeline().addLast(
                                        new EchoServerHandler());
                            }
                    });
            //8
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            //9
            f.channel().closeFuture().sync();
        } finally {
            //10
            group.shutdownGracefully().sync();
        }
    }

}
