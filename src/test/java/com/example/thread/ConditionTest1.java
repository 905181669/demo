package com.example.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 1/22/21 18:48:49
 * @description:
 */
public class ConditionTest1 {

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t0 = new Thread(()->{
            try{
                lock.lock();
                while(true){
                    System.out.println("t0每隔2秒打印一次: " + System.currentTimeMillis()/1000);
                    Thread.sleep(2000);
                    /**
                     * signal只是把在condition wait queue中的firstWaiter的waitStatus设置为-1，
                     * 这样就能在另一个线程的await方法中被唤醒
                     */
                    condition.signal();

                    /**
                     * await中调用release()释放锁并唤醒另外waitStatus=-1的线程，
                     * 然后park起自己并设置自己的线程waitStatus为-2
                     */
                    condition.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });


        Thread t1 = new Thread(()->{
            try{
                lock.lock();

                while(true){
                    System.out.println("我是t1，获取到锁" + System.currentTimeMillis()/1000);
                    Thread.sleep(5000);

                    /**
                     * signal不做唤醒工作，只是把firstWaiter(线程0)的waitStatus设置为-1
                     *
                     * 1.改变firstWaiter的waitStatus为-1
                     * 实现方式：加到sync queue后，这个队列的最后一个node的waitStatus=0
                     * 在doSignal中会把firstWaiter出列，然后把firstWaiter指向nextWaiter
                     *
                     * signalAll会把全部waiter加到sync queue中，只要在sync queue中，线程只能按
                     * FIFO方式被执行
                     */
                    condition.signal();
                    /**
                     * await唤醒其他waitStatus=-1的线程，但是这个场景下，
                     * 当该现场执行到这里时并没有waitStatus=-1的线程
                     *
                     * 1.将当前线程封装为firstWaiter加到condition queue中
                     * 2.fullyRelease释放掉当前线程持有的锁，并unpark在sync queue中的线程
                     * 3.阻塞自己，等待新信号的到来
                     */
                    condition.await();
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        t0.start();


        Thread.sleep(1000);

        t1.start();


    }


}
