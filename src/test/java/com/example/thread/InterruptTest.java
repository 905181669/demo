package com.example.thread;

/**
 * @author: luozijian
 * @date: 6/15/21 20:42:10
 * @description:
 */
public class InterruptTest {

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {

//            Thread.currentThread().interrupt();

            /**
             * 返回当前线程是否被打上interrupt标记，不清除flag，
             */
            System.out.println("interrupted 3: " + Thread.currentThread().isInterrupted());

            try{
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }

            /**
             * 返回当前线程是否被打上interrupt标记，并清除flag，
             * 底层调用的就是： currentThread().isInterrupted(true);
             */
//            System.out.println("interrupted 1: " + Thread.interrupted());

//            System.out.println("interrupted 2: " + Thread.interrupted());


        });

        thread.start();
        Thread.sleep(2000);

        thread.interrupt();
    }

}