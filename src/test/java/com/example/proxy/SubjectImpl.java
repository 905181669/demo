package com.example.proxy;

/**
 * @author: luozijian
 * @date: 1/21/21 10:08:39
 * @description:
 */
public class SubjectImpl implements Subject{

    @Override
    public void hello(String msg) {
        System.out.println("hello: " + msg);
    }
}
