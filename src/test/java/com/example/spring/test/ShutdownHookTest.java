package com.example.spring.test;

/**
 * @author: luozijian
 * @date: 4/15/21 09:06:26
 * @description:
 */
public class ShutdownHookTest {

    public static void main(String[] args) throws Exception{

        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("jvm退出，调用钩子...");
            }
        });
        /**
         * kill -9 杀掉程序不会调用钩子
         */
        System.out.println("测试shutdown hook test");

        Thread.sleep(1000000);
    }
}
