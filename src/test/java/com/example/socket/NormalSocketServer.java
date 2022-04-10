package com.example.socket;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 2020-03-29 19:38:59
 * @description:
 */
public class NormalSocketServer {


    public static void main(String[] args)throws IOException {

        server(9090);

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

                            StringBuilder response = new StringBuilder();
                            //要返回的内容(当前时间)
                            response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                            outputStream.write(response.toString().getBytes());
                        }
                    }

//                    socket.shutdownInput();
//                    socket.shutdownOutput();
                    socket.close();

                }catch (IOException e){
                    e.printStackTrace();
                }

            });


        }

    }


    @Test
    public void testFileInputStream() throws Exception{
        FileInputStream is = new FileInputStream("/Users/luozijian/Downloads/generatorConfig.xml");

//        DataInputStream dis = new DataInputStream(is);
//        System.out.println(dis.readDouble());

        BufferedInputStream bis = new BufferedInputStream(is);

        int len;
        byte[] buffer = new byte[1024];
        while ((len = bis.read(buffer)) != -1){

            System.out.println(new String(buffer,0, len, StandardCharsets.UTF_8));

        }
    }


    @Test
    public void testDataInput() throws Exception{
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("/Users/luozijian/Downloads/java.txt"));
        dos.writeUTF("α");
        dos.writeInt(1234567);
        dos.writeBoolean(true);
        dos.writeShort((short)123);
        dos.writeLong((long)456);
        dos.writeDouble(99.98);
        DataInputStream dis = new DataInputStream(new FileInputStream("/Users/luozijian/Downloads/java.txt"));
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readShort());
        System.out.println(dis.readLong());
        System.out.println(dis.readDouble());
        dis.close();
        dos.close();

    }


}
