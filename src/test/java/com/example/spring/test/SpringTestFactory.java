package com.example.spring.test;

import com.example.spring.bean.Car;
import com.example.spring.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: luozijian
 * @date: 4/10/21 13:28:21
 * @description:
 */
public class SpringTestFactory {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-test-factory.xml");
        // 获取静态工厂创建的car
        Car car = (Car) context.getBean("car");
        // 获取user
        User user = context.getBean(User.class);
        System.out.println(car);
        System.out.println(user);
    }
}
