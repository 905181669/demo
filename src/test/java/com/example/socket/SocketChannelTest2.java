package com.example.socket;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: luozijian
 * @date: 8/27/21 10:06:41
 * @description:
 */
public class SocketChannelTest2 {


    @Test
    public void testRead() throws Exception{

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", 9999));
        if (channel.finishConnect()){ //会循环不断checkConnect

            ByteBuffer buffer = ByteBuffer.allocate(64);
            channel.read(buffer); //非阻塞模式，read会直接返回
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
        }


    }

}