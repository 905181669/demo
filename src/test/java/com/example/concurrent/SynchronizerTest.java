package com.example.concurrent;

/**
 * @author: luozijian
 * @date: 2/2/21 08:33:08
 * @description:
 */
public class SynchronizerTest {

    public synchronized void add(){
        System.out.println(1);
    }


    public void delete(){
        synchronized (this){
            System.out.println(2);
        }
    }

}
