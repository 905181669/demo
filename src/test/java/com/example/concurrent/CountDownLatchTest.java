package com.example.concurrent;

import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 6/18/21 13:44:36
 * @description:
 */
@Slf4j
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception{

        CountDownLatch latch = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(()->{

            try{
                Thread.sleep(2000);
                latch.countDown();
                log.info("完成countDown");
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        executorService.execute(()->{
            try{
                Thread.sleep(5000);
                latch.countDown();
                log.info("完成countDown");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        log.info("主线程阻塞5秒");
        latch.await();


        executorService.shutdownNow();
        log.info("继续往下执行");


    }
}