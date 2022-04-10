package com.example.socket;

import org.springframework.util.CollectionUtils;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: luozijian
 * @date: 5/11/21 08:31:14
 * @description: SocketChannel作为客户端测试（kafka对每个生产者都会有一个专属的selector和SocketChannel）
 */
public class SocketChannelTest {

    public static void main(String[] args) throws Exception{

        Selector selector = Selector.open();

        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 9999));

        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        while (true){
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it =  keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();

                if(key.isReadable()){
                    channel = (SocketChannel)key.channel();
                    readBuff.clear();
                    channel.read(readBuff);
                    readBuff.flip();

                    String receive = new String(readBuff.array(), 0, readBuff.limit());
                    String message = channel.socket().getInetAddress().toString() +":"+
                            channel.socket().getPort() + "发送消息: " + receive;
                    System.out.println(message);


                    readBuff.clear();
                    readBuff.put(message.getBytes());
                    readBuff.flip();
                    key.interestOps(SelectionKey.OP_WRITE);

                }else if(key.isWritable()){


                    channel = (SocketChannel)key.channel();
                    channel.write(readBuff);
                    readBuff.flip();

                    //写完后，设置读兴趣，阻塞
                    key.interestOps(SelectionKey.OP_READ);
                }

            }

        }


    }
}