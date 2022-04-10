package com.example.thread;

import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 1/21/21 14:48:25
 * @description:
 */
public class VolatileTest1 extends Thread {

    boolean flag = false;
    int i = 0;

    @Override
    public void run() {
        while(!flag){
            i++;
            try{
                Thread.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        System.out.println("store i = " + i);

    }

    public static void main(String[] args) throws Exception{
        VolatileTest1 test1 = new VolatileTest1();

        test1.start();

        Thread.sleep(2000);

        test1.flag = true;

        Executors.newFixedThreadPool(1);

        Executors.newCachedThreadPool();


    }
}
