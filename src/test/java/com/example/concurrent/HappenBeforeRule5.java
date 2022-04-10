package com.example.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/19/21 16:42:12
 * @description:
 *
 * 【原则五】线程启动规则
 * 如果线程A调用线程B的start()方法来启动线程B，则start()操作Happens-Before于线程B中的任意操作。
 * 我们也可以这样理解线程启动规则:主线程启动子线程B之后，子线程B能够看到主线程在启动子线程B之前的操作。
 *
 * 要点：为什么主线程修改了x共享变量后，t1后启动却可见x被修改？
 * 个人猜测，根据happen-before 规则5，子线程在启动时，会通知主线程把共享变量都写回到主内存;
 * 线程a为主线程, 线程b为子线程.
 * 那么在启动线程b,调用start方法的时候, a线程执行的所有语句, 对于线程b都是可见的.
 */
public class HappenBeforeRule5 {

    private static int x = 0;

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(()->{
            //输出100
            System.out.println(x);

        });

        x = 100;

        /**
         * 思考：这条规则是不是因为规则4锁定规则导致的呢？
         * 规则4：有线程A和线程B . 线程A最后一步是解锁（解锁会把变量写回到主内存）, 线程B第一步是加锁.
         * B加锁之后, 一定能够看得到A线程解锁之前的所有操作.
         *
         *
         */
        t1.start();

        /**
         * 还有规则7：线程中断规则
         * 一个线程被其他线程interrupt了, 那么检测中断(isInterrupted)
         * 或者抛出InterruptedException一定看得到.
         * 假设没有happens-before 原则，那么可能检测是否中断的状态是不对的, 那么可能线程的运行就会很混乱了,
         */
    }
}