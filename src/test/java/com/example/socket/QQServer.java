package com.example.socket;

import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: luozijian
 * @date: 2020-03-31 23:12:37
 * @description:
 */
public class QQServer {

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(()->{
            try{
                System.out.println("我是一个服务器线程");
                myServer(9000);
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        thread.start();
        System.out.println("主线程执行完毕...");


    }


    public static void myServer(int port) throws Exception{

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);


        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("等待客户端连接...");

        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(1024);

        while (true){
            //阻塞的是调用select()函数的线程
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it =  keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();

                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);

                    //SocketChannel把注册的key放到SelectionKeyManage集合
                    SelectionKey myKey = channel.register(selector, SelectionKey.OP_READ);
                    SelectionKeyManage.addSelectionKey(myKey);

                }else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();

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

                    //收取信息后，设置读兴趣，则调用该channel的write方法
                    key.interestOps(SelectionKey.OP_WRITE);

                }else if(key.isWritable()){

                    Set<SelectionKey> allSelectionKeys = SelectionKeyManage.keys();

                    if(!CollectionUtils.isEmpty(allSelectionKeys)){

                        for (SelectionKey myKey : allSelectionKeys){
                            if(myKey == key){
                                //自己不用写给自己
                                continue;
                            }
                            SocketChannel channel = (SocketChannel)myKey.channel();
                            channel.write(readBuff);
                            readBuff.flip();
                        }

                    }
                    //写完后，设置读兴趣，阻塞
                    key.interestOps(SelectionKey.OP_READ);
                }

            }
        }
    }



    public static class SelectionKeyManage{

        static Set<SelectionKey> keys = Sets.newHashSet();

        public static void addSelectionKey(SelectionKey key){
            keys.add(key);
        }

        public static Set<SelectionKey> keys(){
            return keys;
        }

    }

}
