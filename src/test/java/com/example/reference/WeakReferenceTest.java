package com.example.reference;

import com.example.spring.bean.Car;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author: luozijian
 * @date: 4/28/21 14:59:13
 * @description:
 */
public class WeakReferenceTest {

    public static void main(String[] args) throws Exception{

        String car = new String("car");

        //回收的WeakReference会放到这个队列中
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();

        WeakReference<String> weakCar = new WeakReference<String>(car, referenceQueue);

        /**
         * 1.当一个对象仅仅被weak reference指向, 而没有任何其他strong reference指向的时候,
         * 如果GC运行, 那么这个对象就会被回收
         */
        car = null;
        //弱引用每次gc都会回收
        System.gc();

        //clear，reference = null
//        weakCar.clear();
        //gc后，car对象被回收，weakCar.get()返回null
        System.out.println(weakCar.get());

        // 查看哪些弱引用中的数据被回收了
        while (true) {
            //什么时候放进去的？
            Reference ref =  referenceQueue.poll();
            if (ref == null) {
                break;
            }
            System.out.println("回收了: " + weakCar);
        }

        Thread.sleep(1000);
    }
}