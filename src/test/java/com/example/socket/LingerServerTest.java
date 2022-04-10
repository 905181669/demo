package com.example.socket;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 2020-11-14 17:51:42
 * @description:
 */
public class LingerServerTest {

    static Map<Integer, Socket> sockerMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->{

            sockerMap.forEach((key,socket)->{
                System.out.println(key+"状态:" + (socket.isClosed() ? "关闭" : "未关闭"));
            });
        }, 5L,5L, TimeUnit.SECONDS);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(9999));

        while (true) {
            try{
                Socket socket = serverSocket.accept();

                sockerMap.put(socket.getPort(), socket);

                InputStream input = socket.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1];
                int length;
                while ((length = input.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                    if(output.toByteArray().length >=3){
                        break;
                    }
                }
                String req = new String(output.toByteArray(), "utf-8");
                System.out.println(req);

                //谁先close，谁先发FIN包
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
