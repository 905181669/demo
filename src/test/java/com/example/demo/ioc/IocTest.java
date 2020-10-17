package com.example.demo.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: luozijian
 * @date: 2020-09-10 11:09:04
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Person.class})
public class IocTest {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext context;

    @Autowired
    BeanFactory beanFactory;

    @Test
    public void test(){

//        System.out.println(context.getApplicationName());

        System.out.println(beanFactory.containsBean("person"));
        System.out.println(person);
//        System.out.println(context.getBean(Person.class));
    }



}

//@Component
class Person{

}