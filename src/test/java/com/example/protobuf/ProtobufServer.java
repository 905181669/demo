package com.example.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: luozijian
 * @date: 9/2/21 18:07:32
 * @description:
 */
@Slf4j
public class ProtobufServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ProtoBufHandler());

                    }
                });

        // 绑定端口，同步等待成功
        ChannelFuture future = b.bind(9090).sync();
        log.info("server start in port:[{}]", 9090);
        // 等待服务端链路关闭后,main线程退出
        future.channel().closeFuture().sync();
        // 关闭线程池资源
        boss.shutdownGracefully();

        worker.shutdownGracefully();
    }
}