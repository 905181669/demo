package com.example.socket;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author: luozijian
 * @date: 2020-11-14 17:55:01
 * @description:
 */
public class LingerClientTest {

//    private static int PORT = 9090;
    private static int PORT = 9999;
//    private static String HOST = "192.168.1.79";

    private static String HOST = "127.0.0.1";

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket();

        socket.setTcpNoDelay(true);

        // 测试#1: 默认设置
//        socket.setSoLinger(false, 0);
        // 测试#2
//         socket.setSoLinger(true, 0);
        // 测试#3
//        socket.setSoLinger(true, 1);

        SocketAddress address = new InetSocketAddress(HOST, PORT);
        socket.connect(address);

        OutputStream output = socket.getOutputStream();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            sb.append("hel");
        }
        byte[] request = sb.toString().getBytes("utf-8");

        output.write(request);

        long start = System.currentTimeMillis();
        socket.close();
        long end = System.currentTimeMillis();
        System.out.println("close time cost: " + (end - start));


    }




}
