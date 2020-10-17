package com.example.demo.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: luozijian
 * @date: 2020-09-20 12:04:59
 * @description:
 */
public class XmlIocTest {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("myApplication.xml");

        System.out.println(app);
    }
}
