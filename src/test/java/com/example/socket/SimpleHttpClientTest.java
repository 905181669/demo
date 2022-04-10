package com.example.socket;

import org.apache.http.util.ByteArrayBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLEncoder;

/**
 * @author: luozijian
 * @date: 9/5/21 15:28:58
 * @description: 一个最简单的http client
 */
public class SimpleHttpClientTest {

    private static  int flag = 0;

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9090));


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


        OutputStream out = socket.getOutputStream();
        //发送请求
        out.write(buf.toString().getBytes());
//        out.flush();
        Thread.sleep(100);
        toString(socket.getInputStream());


        /**
         * 奇怪的知识：
         * 如果服务器端设置了Connection: close，在正常的4次挥手后，为什么这里out.write不会报错？
         * 实验：到这里时，客户端的socket处于CLOSE_WAIT状态，当调用write后，会发送FIN包给tomcat释放连接。
         * 但是服务端为什么没有TIME_WAIT状态？
         * 原来是RST！
         */
//        out.write(buf.toString().getBytes());
//        out.flush();

//        Thread.sleep(100);
//        toString(socket.getInputStream());

        /**
         * 此时服务端已经发起了主动关闭，客户端调用close后也发送FIN包，服务端的socket进入TIME_WAIT状态
         * 如果不调用close，当程序关闭时也会主动调用close
         */
//        Thread.sleep(10000);
//        socket.close();

//        System.in.read();

    }

    /**
     * tcp是字节流，如果要复用http连接，就需要按每次返回的http报文的内容长度读完一个报文后返回，
     * 不能按平常read() != -1这样读，因为-1是socket close时才会返回
     * @param is
     * @return
     * @throws Exception
     */
    public static void toString(InputStream is) throws Exception{

        ByteArrayBuffer buffer = new ByteArrayBuffer(1024 * 10);

        //用available()其实不准，因为available是立即返回可读的字节大小
        flag = is.available();
        byte[] tmp = new byte[flag];

        is.read(tmp);
        buffer.append(tmp, 0, flag);

        byte[] var6 = buffer.toByteArray();

        System.out.println(new String(var6));
        System.out.println("-------------华丽分割线----------");
    }



}