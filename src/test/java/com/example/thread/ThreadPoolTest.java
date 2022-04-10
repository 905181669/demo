package com.example.thread;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author: luozijian
 * @date: 7/28/21 09:28:01
 * @description:
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(()->{

            try{
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(8080));

                InputStream is = socket.getInputStream();
                int ret = is.read();
                System.out.println(ret);

            }catch (Exception e){
                e.printStackTrace();
            }
        });

//        executorService.execute(()->{
//            System.out.println(1);
//            try{
//                Thread.sleep(1000000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });

    }


    @Test
    public void testSchedule() throws Exception{

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        ScheduledThreadPoolExecutor service = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(task, 1, 10, TimeUnit.SECONDS);
        System.in.read();
    }

}