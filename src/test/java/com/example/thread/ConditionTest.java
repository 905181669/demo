
package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    static class NumberWrapper {
        public int value = 0;
    }

    public static void main(String[] args) throws Exception {
        //初始化可重入锁
        final Lock lock = new ReentrantLock();

        final Condition reachThreeCondition = lock.newCondition();


        //NumberWrapper只是为了封装一个数字，一边可以将数字对象共享，并可以设置为final
        //注意这里不要用Integer, Integer 是不可变对象
        final NumberWrapper num = new NumberWrapper();
        //初始化A线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //需要先获得锁
                lock.lock();
                try {
                    //输出双数
                    while (true) {
                        if(num.value % 2 == 0){
                            System.out.println("线程A打印双数：" + num.value);
                            num.value++;
                        }else {
                            reachThreeCondition.signal();
                            reachThreeCondition.await();
                        }

                        Thread.sleep(1000);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }

            }

        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    //输出双数
                    while (true) {
                        if(num.value % 2 != 0){
                            System.out.println("线程B打印单数：" + num.value);
                            num.value++;
                        }else{
                            reachThreeCondition.signal();
                            reachThreeCondition.await();
                        }
                        Thread.sleep(1000);

                    }

                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        });

        threadA.setName("线程A");
        threadB.setName("线程B");
        //启动两个线程
        threadA.start();

        Thread.sleep(1000);
        threadB.start();
    }
}