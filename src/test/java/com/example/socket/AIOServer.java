package com.example.socket;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import sun.nio.ch.SelectorProviderImpl;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: luozijian
 * @date: 2020-04-20 21:47:12
 * @description:
 */
public class AIOServer {

    private final int port;

    public static void main(String[] args) {
        int port = 9000;
        new AIOServer(port);
    }

    public AIOServer(int port) {
        this.port = port;

//        Thread thread = new Thread(()->{
            listen();
//        });
//        thread.start();
    }


    private void listen(){
        try{
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("服务已启动，监听端口：" + port);

            Future<AsynchronousSocketChannel> accept;

            while (true){
                accept = server.accept();
                System.out.println("=================");
                System.out.println("服务器等待连接...");
                AsynchronousSocketChannel socketChannel = accept.get();// get()方法将阻塞。

                System.out.println("服务器接受连接");
                System.out.println("服务器与" + socketChannel.getRemoteAddress() + "建立连接");

                ByteBuffer buffer = ByteBuffer.wrap("zhangphil\n".getBytes());
                Future<Integer> write=socketChannel.write(buffer);

                while(!write.isDone()) {
                    Thread.sleep(10);
                }

                System.out.println("服务器发送数据完毕.");
                socketChannel.close();

            }





//
//            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
//                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//
//                @Override
//                public void completed(AsynchronousSocketChannel result, Object attachment) {
//                    System.out.println("I/O操作成功，开始获取数据");
//                    try{
//                        buffer.clear();
//                        result.read(buffer).get();
//                        buffer.flip();
//                        result.write(buffer);
//                        buffer.flip();
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }finally {
//                        IOUtils.closeQuietly(result);
//                    }
//                    System.out.println("操作完成");
//                }
//
//
//                @Override
//                public void failed(Throwable exc, Object attachment) {
//                    System.out.println("I/O操作失败：" + exc);
//                }
//            });



//            try{
//                System.out.println("主线程进入睡眠");
//                Thread.sleep(20000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
