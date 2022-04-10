package com.example.concurrent;

/**
 * @author: luozijian
 * @date: 7/12/21 09:14:24
 * @description:
 */
public class SynchronizeTest {

    public static void main(String[] args) {

        Object lock = new Object();
        Thread a = new Thread(()->{

            synchronized (lock){
                System.out.println(System.currentTimeMillis()/1000 + ": " + Thread.currentThread());

                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        Thread b = new Thread(()->{

            synchronized (lock){
                System.out.println(System.currentTimeMillis()/1000 + ": " + Thread.currentThread());

                try{
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        a.start();

        b.start();
    }
}