package com.example.concurrent;

/**
 * @author: luozijian
 * @date: 7/12/21 08:33:17
 * @description:
 */
/**
 * @description: 参考了 https://www.jianshu.com/p/da312eee4ac4
 * @author: alonwang
 * @create: 2019-07-19 15:54
 *
 * 什么是虚假唤醒，为什么wait需要放到while循环中？
 *
 * 举个例子：
 * 1.消费者线程1获取到锁，执行到wait()，线程1进入wait set队列；
 * 2.假如生产者线程3拿到了线程1释放的锁，然调用notify()；
 * 3.消费者线程2由于获取不到锁(被线程3拿去了)，阻塞在Contention list中；
 *
 * 4.线程3释放锁，并notify，线程1被放到锁池(contention list)，
 * 但是线程2有可能获取到锁，从Contention list中返回并执行完；而收到notify的线程1此后也
 * 获取到锁，如果不继续判断竞态条件，就会发生超消费的情况；
 *
 * 核心点就是wait set队列和contention list队列获取到锁的方式不一样，
 *
 *
 *
 **/
public class SpuriousWakeUp {

    private final Object lock = new Object();
    private int product = 0;

    //如果没有产品,在lock对象上等待唤醒,如果有产品,消费.
    private Runnable consumer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare consume");
        synchronized (lock) {
            if (product <= 0) {//替换为while解决线程虚假唤醒问题
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " wakeup");
            }
            product--;

            if(product < 0){
                try{
                    throw new Exception("消费超了");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + " consumed product:" + product);
            if (product < 0) {
                System.err.println(Thread.currentThread().getName() + " spurious lock happend, product:" + product);
            }
        }
    };

    //生产一个产品然后唤醒一个在lock对象上等待的consumer
    private Runnable producer = () -> {
        System.out.println(Thread.currentThread().getName() + " prepare produce");
        synchronized (lock) {
            product += 1;
            System.out.println(Thread.currentThread().getName() + " produced product:" + product);
            lock.notify();
        }
    };

    public void producerAndConsumer() {
        // 启动2个consumer,1个producer
        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);
        Thread p = new Thread(producer);
        c1.start();
        c2.start();
        p.start();

    }

    public static void main(String[] args) {
        //运行100次,以便触发异常现象
        for (int i = 0; i < 100; i++) {
            new SpuriousWakeUp().producerAndConsumer();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}