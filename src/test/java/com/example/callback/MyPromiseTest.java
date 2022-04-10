package com.example.callback;

import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 9/1/21 09:56:35
 * @description:
 */
public class MyPromiseTest {

    public static void main(String[] args) throws Exception{

        MyPromise myPromise = MyExecutor.execute(()->{
            System.out.println("执行耗时任务-" + Thread.currentThread().getName());
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        myPromise.addListener(new MyListener() {
            @Override
            public void onComplete() {
                System.out.println("执行任务完毕回调-" + Thread.currentThread().getName());
            }
        });



    }
}