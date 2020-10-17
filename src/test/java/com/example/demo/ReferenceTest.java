package com.example.demo;

import java.lang.ref.SoftReference;


/**
 * @author: luozijian
 * @date: 2020-06-24 10:05:59
 * @description:
 */
public class ReferenceTest {


    public static void main(String[] args) {

        C c = new C();
        D d = new D(c);
//        c = null;
        System.gc();
        System.out.println(d.getC());  // null

    }
}


class C {

}

class D {

//    WeakReference<C> weakReference;

    SoftReference<C> weakReference;
    public D(C c) {
        weakReference = new SoftReference<>(c);
    }

    public C getC() {
        return weakReference.get();
    }
}
