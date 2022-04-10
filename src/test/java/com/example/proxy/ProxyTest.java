package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: luozijian
 * @date: 1/21/21 10:10:13
 * @description:
 */
public class ProxyTest {

    /**
     * 步骤1：创建接口类和实现类
     * 步骤2：创建代理类
     * 步骤3：创建代理类实例(代理类实例中组合了实现类的实例)
     * 步骤4：Proxy创建（接口+代理类）的实例，这个实例实现了接口类
     * 步骤5：执行步骤四实例中的方法
     *
     * 调用hello时最终调用subjectProxy中的invoke方法
     * @param args
     */
    public static void main(String[] args) {

        Subject subject = new SubjectImpl();
        InvocationHandler subjectProxy = new SubjectProxy(subject);
        Subject proxyInstance = (Subject) Proxy
                .newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.hello("world");
    }
}
