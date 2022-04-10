package com.example.thread;

/**
 * @author: luozijian
 * @date: 6/15/21 20:21:26
 * @description:
 */
public class JoinTest {

    public static void main(String[] args) throws Exception{

        Thread t = new Thread(()->{

            System.out.println("子线程");
            try{
                Thread.sleep(3000);
            }catch (Exception e){e.printStackTrace();}

        });

        t.start();

        t.join();

        System.out.println("主线程");

        System.out.println(0&2);
        System.out.println(1&2);
        System.out.println(2&2);
        System.out.println(3&2);
        System.out.println(4&2);
        System.out.println(5&2);
        System.out.println(6&2);
        System.out.println(7&2);
        System.out.println(8&2);
        System.out.println(9&2);
    }
}