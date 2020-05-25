package com.example.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 2020-03-29 21:59:29
 * @description:
 */
public class ServerSocketChannelTest {


    public static void main(String[] args) throws Exception{

        server1(9000);
    }

    public static void server(int port) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        //设置accept()不会阻塞
        serverSocketChannel.configureBlocking(false);

        System.out.println("等待客户端连接...");
        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel != null) {
                System.out.println("接收到客服端连接：" + socketChannel.getRemoteAddress());
                executorService.execute(() -> {
                    try {

//                        Socket socket = socketChannel.socket();
//                        InputStream inputStream = socket.getInputStream();
//                        OutputStream outputStream = socket.getOutputStream();
//                        int len;
//                        byte[] buffer = new byte[1024];
//                        while ((len = inputStream.read(buffer)) != -1) {
//                            System.out.println(inputStream.available());
//
//                            StringBuilder sb = new StringBuilder();
//                            sb.append(new String(buffer, 0, len, "UTF-8"));
//                            System.out.println("收到客服端信息：" + sb);
//
//                            if (sb.toString().startsWith("end")) {
//                                outputStream.write("关闭".getBytes());
//                                break;
//                            }
//                        }
//
//                        socket.shutdownInput();
//                        socket.shutdownOutput();
//                        socket.close();



                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int bytesRead  = socketChannel.read(buf);
                        while(bytesRead>0){
                            buf.flip();
                            while(buf.hasRemaining()){
                                System.out.print((char)buf.get());
                            }

                            buf.clear();
                            bytesRead = socketChannel.read(buf);
                        }

                        socketChannel.shutdownInput();
                        socketChannel.shutdownOutput();
                        socketChannel.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                });

            }
        }

    }




    public static void server1(int port) throws Exception{

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            // 注册 channel，并且指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("等待客户端连接...");

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            while (true) {
                int nReady = selector.select(); //阻塞，一旦有感兴趣的事，就继续执行
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("received : " + new String(readBuff.array()));
//                        key.interestOps(SelectionKey.OP_WRITE);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    else if (key.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }







    @Test
    public void testFileChannel() throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("/Users/luozijian/Downloads/generatorConfig.xml", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf);

        StringBuilder sb = new StringBuilder();
        while (bytesRead != -1){
            buf.flip();
            sb.append(new String(buf.array(), 0, buf.limit(), StandardCharsets.UTF_8));
            bytesRead = inChannel.read(buf);
        }
        System.out.println(sb);
    }


    @Test
    public void test1(){
        System.out.println(1 & ~13);
        System.out.println(4 & ~13);
        System.out.println(8 & ~13);
        System.out.println(16 & ~13);

        System.out.println(SelectionKey.OP_READ
                   | SelectionKey.OP_WRITE
                     | SelectionKey.OP_CONNECT);


    }
}
