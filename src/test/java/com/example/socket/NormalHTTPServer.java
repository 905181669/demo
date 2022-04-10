package com.example.socket;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 2020-03-29 19:38:59
 * @description:
 */
public class NormalHTTPServer {


    public static void main(String[] args)throws IOException {

        server(9000);

    }


    public static void server(int port) throws IOException {

        ExecutorService executor = Executors.newFixedThreadPool(100);


        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("等待客户端连接...");
        while (true){

            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功：" + socket.getInetAddress());

            executor.execute(()->{

                try{
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = inputStream.read(buffer)) != -1){
//                        System.out.println(inputStream.available());

                        StringBuilder sb = new StringBuilder();
                        sb.append(new String(buffer, 0, len, "UTF-8"));
                        System.out.println("收到客服端信息：" + sb);

                        if(sb.toString().startsWith("end")){
                            outputStream.write("关闭".getBytes());
                            break;
                        }else {

                            /**
                             * 返回一个http 响应
                             */
                            StringBuilder response = new StringBuilder();
                            //制作响应报文

                            //响应状态
                            response.append("HTTP/1.1 200 OK\r\n");

                            //响应头
                            response.append("Content-type:text/html\r\n");
                            response.append("Access-Control-Allow-Origin: *\r\n");
                            response.append("Access-Control-Allow-Credentials: true\r\n");

                            response.append("\r\n");

                            //要返回的内容(当前时间)
                            response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                            outputStream.write(response.toString().getBytes());
                        }
                    }

                    socket.close();

                }catch (IOException e){
                    e.printStackTrace();
                }

            });


        }

    }



}
