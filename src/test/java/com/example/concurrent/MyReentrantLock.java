package com.example.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: luozijian
 * @date: 1/23/21 08:19:50
 * @description:
 *
 * 1.非公平锁在获取锁的时候是先直接进行CAS替换state的值，如果替换成功则获取到锁，并不进行排序操作
 *
 * 2.公平锁在获取锁的时候通过hasQueuedPredecessors() 方法判断是否有其它线程等待获取锁的时间超过当前线程，
 * 如果有则表明同步队列中已有线程先行进行等待获取锁。
 *
 * 3.非公平锁的非公平只体现在第一次获取锁的时候，如果第一次获取不到锁，那么则进入同步队列，
 * 同步队列中的线程获取锁的顺序则是先进先出的。
 *
 */
public class MyReentrantLock {

    private NonfairSync sync;

    public MyReentrantLock(){
        sync = new NonfairSync();
    }

    public void lock() {
        sync.lock();
    }

    public void unlock(){
        sync.release(1);
    }

    static final class NonfairSync extends AbstractQueuedSynchronizer{

        public void lock(){
            if(compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
            }else {
                acquire(1);
            }
        }


        /**
         * v1.0 先不实现可重入
         * @param arg
         * @return
         */
        @Override
        protected final boolean tryAcquire(int arg) {
            final Thread thread = Thread.currentThread();
            int c = getState();
            if(c == 0){
                compareAndSetState(0, 1);
                setExclusiveOwnerThread(thread);
                return true;
            }else {
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int releases) {
            int c = getState() - releases;
            boolean free = false;
            if(c == 0){
               free = true;
               setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;

        }

    }

}
