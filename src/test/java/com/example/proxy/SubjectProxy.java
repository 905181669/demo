package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: luozijian
 * @date: 1/21/21 10:09:16
 * @description:
 */
public class SubjectProxy implements InvocationHandler {

    private Subject subject;

    public SubjectProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------begin-------------");
        Object invoke = method.invoke(subject, args);
        System.out.println("--------------end-------------");
        return invoke;
    }

}
