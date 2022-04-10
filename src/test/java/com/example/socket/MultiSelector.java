package com.example.socket;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: luozijian
 * @date: 7/21/21 10:01:48
 * @description:
 */

@Slf4j
public class MultiSelector {


    public static void main(String[] args) throws Exception{


        Selector acceptSelector = Selector.open();
        Selector readWriteSelector = Selector.open();


        accept(7777, acceptSelector, readWriteSelector);

        Thread.sleep(1000);

        handle(readWriteSelector);



    }

    public static void accept(int port, Selector acceptSelector, Selector readWriteSelector) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(port));

        log.info("监听端口：" + port);

        serverSocketChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);

        Thread listenThread = new Thread(()->{

            try{
                while (true){
                    acceptSelector.select();
                    Set<SelectionKey> keys = acceptSelector.selectedKeys();

                    Iterator<SelectionKey> it = keys.iterator();
                    while(it.hasNext()){
                        SelectionKey key = it.next();
                        it.remove();

                        if(key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();

                            log.info("新的客户端：" + socketChannel.socket().getPort());
                            socketChannel.configureBlocking(false);
                            socketChannel.register(readWriteSelector, SelectionKey.OP_READ);
                        }
                    }


                }
            }catch (Exception e){
                e.printStackTrace();
            }


        });

        listenThread.setName("acceptor-thread");
        listenThread.start();


    }


    public static void handle(Selector selector) throws Exception{

        Thread handleThread = new Thread(()->{
            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            try{

                while(true){
                    int ret = selector.select(5000);
                    log.info("需要处理的IO连接数量：" + ret);

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if(key.isReadable()){
                            SocketChannel channel = (SocketChannel) key.channel();
                            readBuff.clear();
                            channel.read(readBuff);

                            readBuff.flip();

                            byte[] bytes = new byte[readBuff.limit()];
                            readBuff.get(bytes);
                            log.info("接收：" + new String(bytes));

                            key.interestOps(SelectionKey.OP_READ);
                        }

                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        });

        handleThread.setName("io-thread");
        handleThread.start();


    }




}