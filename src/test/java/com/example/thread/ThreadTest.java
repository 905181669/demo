package com.example.thread;

/**
 * @author: luozijian
 * @date: 2/4/21 15:31:54
 * @description:
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试");
                try{
                    Thread.sleep(1000000000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        thread.start();

        Thread.sleep(1000000000);
    }
}
