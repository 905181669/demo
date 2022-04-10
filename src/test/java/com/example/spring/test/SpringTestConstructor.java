package com.example.spring.test;

import com.example.spring.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: luozijian
 * @date: 4/10/21 13:28:21
 * @description:
 */
public class SpringTestConstructor {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-test-constructor.xml");
        // 获取user这个bean
        User user = context.getBean(User.class);
        // 输出产看结果
        System.out.println(user);
    }
}
