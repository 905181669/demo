package com.example.socket;

import java.net.Socket;

/**
 * @author: luozijian
 * @date: 2022/3/25
 * @description:
 */
public class SocketTest {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.setReceiveBufferSize(1024);
        System.out.println(socket.getSendBufferSize());
        System.out.println(socket.getReceiveBufferSize());
    }
}