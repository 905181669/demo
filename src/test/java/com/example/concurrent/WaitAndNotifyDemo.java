package com.example.concurrent;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 2022/3/14
 * @description:
 */
public class WaitAndNotifyDemo {

    public static void main(String[] args) {

        Queue queue = new LinkedList();
        int maxSize = 1;

        Thread producer = new Thread(()->{
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "-队列满了，进入wait状态");
                            queue.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    queue.notifyAll();
                }
            }

        });
        producer.setName("生产者");

        Thread consumer = new Thread(()->{

            while(true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try{
                            System.out.println(Thread.currentThread().getName() + "-队列空了，进入wait状态");
                            queue.wait();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    queue.notifyAll();

                    try{
                        TimeUnit.SECONDS.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        consumer.setName("消费者");

        producer.start();
        consumer.start();

    }
}

