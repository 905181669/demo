package com.example.spring.test;

import com.example.spring.bean.Car;
import com.example.spring.context.MyClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author: luozijian
 * @date: 4/12/21 21:26:49
 * @description:
 */
public class MyClassPathXmlApplicationContextTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new MyClassPathXmlApplicationContext("spring-test-set.xml");



        System.out.println(ctx.getEnvironment().getProperty("VAR"));

        GenericApplicationContext gtx = new GenericApplicationContext(ctx);
        gtx.refresh();

        /**
         * 从gtx的beanFactory找不到，再从父的beanFactory找
         */
        gtx.getBean("myCar");

        System.out.println(gtx.getBeanFactory() == ctx.getBeanFactory());


//        System.out.println(gtx.getBean("apple"));




//        ctx.registerShutdownHook(); //注册钩子，这样jvm退出时可以优雅关闭容器
//        ctx.close(); //触发关闭容器

    }
}
