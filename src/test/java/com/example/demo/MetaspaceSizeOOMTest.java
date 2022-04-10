package com.example.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: luozijian
 * @date: 8/18/21 07:48:57
 * @description:
 */
public class MetaspaceSizeOOMTest {

    public static void main(String[] args) {

        try {
            while (true){
                Enhancer enhancer=new Enhancer();
                enhancer.setSuperclass(Test.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}