package com.example.socket;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 6/8/21 13:50:36
 * @description:
 */
public class SocketChannelTest1 {

    public static void main(String[] args) throws Exception{

//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();

        /**
         * ServerSocketChannel只能注册accept事件,
         * SocketChannel可以注册read,write,connect三个事件
         */

//        serverSocketChannel.register(selector, SelectionKey.OP_READ);

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);


        Executors.newFixedThreadPool(1).execute(()->{

            try{
                /**
                 * 如果不connect，对socketChannel的读和写都会报错
                 */
                Thread.sleep(2000);
                ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//                byteBuffer.put("hello".getBytes());
//                socketChannel.read(byteBuffer);
            }catch (Exception e){
                e.printStackTrace();
            }
        });


        while (true){
            int ret = selector.select();
            System.out.println("io事件就绪channel数：" + ret);

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();

                if(key.isWritable()){
                    SocketChannel ssc = (SocketChannel) key.channel();
                    System.out.println("writeable。。。");
                }else if(key.isReadable()){
                    SocketChannel ssc = (SocketChannel) key.channel();
                    System.out.println("readable。。。");
                }


            }



        }

    }
}