package com.example.reference;

import com.example.spring.bean.Car;

import java.lang.ref.WeakReference;

/**
 * @author: luozijian
 * @date: 7/6/21 09:22:43
 * @description:
 *
 * WeakReference，弱引用，gc即回收
 * SoftReference，软引用，内存吃紧才会回收
 *
 * 强引用-->软引用-->弱引用-->虚引用
 *
 */
public class TestWeakReference {

    public static void main(String[] args) {
        Car car = new Car();
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i=0;

        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);

                /**
                 * 把强引用去掉，GC后, 那么这个对象就会被回收
                 */
                if(i > 200000){
                    car = null;
                }
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

}