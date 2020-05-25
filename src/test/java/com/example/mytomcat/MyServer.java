package com.example.mytomcat;



import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author: luozijian
 * @date: 2020-04-30 22:10:40
 * @description:
 */
public class MyServer {

    private ServerSocket serverSocket;


    public static void main(String[] args) throws Exception{
        MyServer myServer = new MyServer();
        myServer.init(8080);
        myServer.start();

    }

    /**
     * 初始化
     */
    public void init(int port) throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        //默认接收缓冲池128k
        System.out.println(serverSocket.getReceiveBufferSize());

    }

    /**
     * 启动方法
     */
    public void start() throws IOException{
        System.out.println("启动服务器成功，绑定端口号：" + serverSocket.getLocalPort());
        while (true){
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();

            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[1024];
            int len;
            if((len = is.read(bytes)) > 0){
                sb.append(new String(bytes, 0, len));
            }
            System.out.println(sb);
            System.out.println(socket.getSendBufferSize()/1024);
            socket.getOutputStream().write(sb.toString().getBytes());

            is.close();
            socket.close();
        }
    }
}
