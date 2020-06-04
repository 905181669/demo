package com.example.thread;

/**
 * @author: luozijian
 * @date: 2020-06-01 22:08:54
 * @description:
 */
public class ThreadTest {


    public static void main(String[] args) throws Exception{

        MyThread t = new MyThread();
        t.start();


        Thread.sleep(100);
        t.interrupt();

//        boolean flag = t.isInterrupted();
        System.out.println(t.isInterrupted());


        System.out.println("主线程执行完毕");


    }


    public static class MyThread extends Thread{

        private boolean stopped = false;
        @Override
        public void run() {


            try{


//                for(int i = 0; i < 1895650; i++){
//                    System.out.println(i);
//                }
//                System.out.println("测试" + Thread.currentThread().interrupted());
//                System.out.println("测试" + Thread.currentThread().interrupted());
//                System.out.println(Thread.currentThread().isInterrupted());
//                System.out.println(Thread.currentThread().isInterrupted());
                Thread.sleep(10000);
            }catch (Exception e){
                System.out.println("测试 " + Thread.currentThread().isInterrupted());
                e.printStackTrace();

            }

            System.out.println("子线程继续执行");

        }

        public void setStopped(){
            this.stopped = true;
        }
    }

}
