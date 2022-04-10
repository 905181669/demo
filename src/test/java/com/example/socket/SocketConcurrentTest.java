package com.example.socket;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: luozijian
 * @date: 9/9/21 08:43:59
 * @description: 测试一下socket并发连接
 */
public class SocketConcurrentTest {

    public static void main(String[] args) {

        Socket socket = new Socket();
        Thread t1 = new Thread(()->{
            try{
                socket.connect(new InetSocketAddress("localhost", 9090));
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(()->{
            try{
                socket.connect(new InetSocketAddress("localhost", 9090));
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();

    }
}