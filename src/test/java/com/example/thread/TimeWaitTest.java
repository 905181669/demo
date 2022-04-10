package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/17/21 17:26:20
 * @description:
 */
public class TimeWaitTest {

    /**
     * jstack pid
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //java.lang.Thread.State: TIMED_WAITING (sleeping)
//        TimeUnit.SECONDS.sleep(86400);

//        synchronized (TimeWaitTest.class){
            //java.lang.Thread.State: WAITING (on object monitor)
//            TimeWaitTest.class.wait();
//        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("shutdown hook!!!");
            }
        }));


        Object obj = new Object();

        Thread t1 = new Thread(()->{

            synchronized (obj){
                try{
                    /**
                     *
                     * 当t2先获取到锁，进入TIME_WAITING，
                     * t1则进入阻塞状态：  java.lang.Thread.State: BLOCKED (on object monitor)
                     */

                    TimeUnit.SECONDS.sleep(86400);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        Thread t2 = new Thread(()->{

            synchronized (obj){

                try{
                    //不释放锁
//                    TimeUnit.SECONDS.sleep(86400);
                    while (true){
                        System.out.println(System.currentTimeMillis());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        t2.setDaemon(false);
//        t1.start();
        t2.start();

//        t1.join();
        t2.join();

    }
}