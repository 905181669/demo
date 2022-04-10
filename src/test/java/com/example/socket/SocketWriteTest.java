package com.example.socket;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: luozijian
 * @date: 9/9/21 08:59:07
 * @description:
 */
public class SocketWriteTest {

    public static void main(String[] args) throws Exception{

        StringBuffer buf = new StringBuffer();
        buf.append("GET /shortTime HTTP/1.1");
        buf.append("\r\n");
        buf.append("Content-Type: text/plain");
        buf.append("\r\n");
        buf.append("User-Agent: restclient/1.0");
        buf.append("\r\n");
        buf.append("Connection: keep-alive"); //保持连接，单路复用
        buf.append("\r\n");
        buf.append("Accept-Encoding: *;q=0"); //不支持任何压缩编码
        buf.append("\r\n");
        buf.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        buf.append("\r\n");
        buf.append("Host: " + "localhost" + ":" + 9090);
        buf.append("\r\n");
        buf.append("Content-Length: 0");
        buf.append("\r\n");
        buf.append("\r\n");


        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9090));

        /**
         * 测试输入输出流关闭了socket的读写情况
         */
        Thread t1 = new Thread(()->{
            try{
                get(socket, buf.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(()->{
            try{
                get(socket, buf.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();



    }


    public static void get(Socket socket, String req) throws Exception{

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();


        try{
            os.write(req.getBytes());
            os.flush();
            Thread.sleep(1000);
            SimpleHttpClientTest.toString(is);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            IOUtils.closeQuietly(os);
//            IOUtils.closeQuietly(is);
        }

    }



}