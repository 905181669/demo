package com.example.concurrent;

import com.example.thread.MyUnsafe;
import sun.misc.Unsafe;


import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: luozijian
 * @date: 1/23/21 08:49:50
 * @description:
 */
public class MyQueuedSynchronizer extends AbstractOwnableSynchronizer {

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static final long stateOffset;
    private static final long waitStatusOffset;

    static {
        try{
            stateOffset = unsafe.objectFieldOffset
                    (MyQueuedSynchronizer.class.getDeclaredField("state"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (MyQueuedSynchronizer.Node.class.getDeclaredField("waitStatus"));
        }catch (Exception e){
            throw new Error(e);
        }

    }




    private volatile int state;

    volatile Node head;

    volatile Node tail;


    public void lock(){
        if(unsafe.compareAndSwapInt(this, stateOffset, 0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());
        }else {
            acquire(1);
        }
    }

    private void acquire(int arg){
        if(!tryAcquire(arg)){
            acquireQueued(addWaiter(), 1);
        }



    }

    /**
     * 再次尝试获取锁
     * @param arg
     * @return
     */
    private boolean tryAcquire(int arg){
        Thread thread = Thread.currentThread();
        int c = state;
        if(c == 0){
            unsafe.compareAndSwapInt(this, stateOffset, 0, arg);
            setExclusiveOwnerThread(thread);
            return true;
        }else {
            return false;
        }
    }


    private void acquireQueued(final Node node, int arg){

        Node p = node.prev;
        if(p == head && tryAcquire(arg)){
            head = node;
            node.thread = null;
            node.prev = null;
            p.next = null;
        }
        if(p.waitStatus == 0){
            unsafe.compareAndSwapInt(p, waitStatusOffset, 0, -1);
        }
        LockSupport.park(this);

    }

    private Node addWaiter(){
        Node node = new Node(Thread.currentThread());
        Node pred = tail;

        //to be continue
        return null;

    }



    static final class Node{

        static final int SIGNAL = -1;

        volatile int waitStatus;

        volatile Thread thread;

        volatile Node prev;

        volatile Node next;

        public Node(Thread thread){
            this.thread = thread;
        }

    }

}

