package com.example.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/19/21 15:58:17
 * @description:
 */
public class VolatileTest {

    private static int count;

    private static boolean flag = true;

    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(()->{

            while (flag){
                /**
                 * 要点1：为什么flag不适用volatile修饰，线程1也能读到flag？
                 * 因为System.out.println使用了synchronized，保证了flag可见性；符合happen-before【原则四】锁定规则
                 *
                 * 假如把System.out.println(System.currentTimeMillis());注释掉，t1不会退出,
                 * 因为t1的工作线程看不到被主线程修改的flag
                 *
                 * 要点2：要想让t1退出，flag加volatile修饰即可
                 */
//                System.out.println(System.currentTimeMillis());
                writeBackFlagToMain();
            }
            System.out.println("线程t1退出");

        });

        t1.start();

        TimeUnit.SECONDS.sleep(2);

        flag = false;

        System.out.println("主线程退出");

    }


    /**
     * synchronized中访问flag变量，每次进入synchronized代码块时都是从主内存读的flag
     */
    private static void writeBackFlagToMain(){
        synchronized (VolatileTest.class){
            boolean flag1 = flag;
        }
    }
}