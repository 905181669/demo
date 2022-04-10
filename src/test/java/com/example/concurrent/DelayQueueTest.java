package com.example.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/19/21 09:26:02
 * @description:
 * 原理：利用take()-->condition.awaitNanos(delay)-->;
 */
public class DelayQueueTest {

    private static DelayQueue delayQueue  = new DelayQueue();

    public static void main(String[] args) throws Exception{

        delayQueue.offer(new MyDelayedTask("task1",6000));

        /**
         * ScheduleThreadPoolExecutor就是利用延时队列实现定时功能
         */
        System.out.println("take之前的时间= " + LocalDateTime.now());
        Delayed take = delayQueue.take();
        System.out.println("take之后的时间= " + LocalDateTime.now());
        System.out.println(take);

    }
}


/**
 *  compareTo 方法必须提供与 getDelay 方法一致的排序
 */
class MyDelayedTask implements Delayed {

    private String name ;
    private long start = System.currentTimeMillis();
    private long time ;

    public MyDelayedTask(String name,long time) {
        this.name = name;
        this.time = time;
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        MyDelayedTask o1 = (MyDelayedTask) o;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MyDelayedTask{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}

/**
 *
 * public E take() throws InterruptedException {
 *         final ReentrantLock lock = this.lock;
 *         lock.lockInterruptibly(); //上锁
 *         try {
 *             for (;;) {
 *                 E first = q.peek(); //如果队列为空，无限期等待
 *                 if (first == null)
 *                     available.await();
 *                 else {
 *                     long delay = first.getDelay(NANOSECONDS); //队列不为空，获取第一个task的延迟时间
 *                     if (delay <= 0) //如果时间到了，直接返回队首任务
 *                         return q.poll();
 *                     first = null;
 *                     if (leader != null) //不知道这个leader干嘛用
 *                         available.await();
 *                     else {
 *                         Thread thisThread = Thread.currentThread();
 *                         leader = thisThread;
 *                         try {
 *                             available.awaitNanos(delay); //等待delay时间
 *                         } finally {
 *                             if (leader == thisThread)
 *                                 leader = null;
 *                         }
 *                     }
 *                 }
 *             }
 *         } finally {
 *             if (leader == null && q.peek() != null)
 *                 available.signal();
 *             lock.unlock();
 *         }
 *     }
 *
 *
 *
 */