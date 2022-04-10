package com.example.spring.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author: luozijian
 * @date: 2021/12/9 07:45:19
 * @description:
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        builder.addPropertyValue("speed", 100);
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.setScope("prototype");
        beanDefinition.setLazyInit(true);
        beanDefinition.getPropertyValues().addPropertyValue("price", 1);

        System.out.println(beanDefinition);

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(Car.class);
        genericBeanDefinition.setLazyInit(true);

//        genericBeanDefinition.setPropertyValues();
        System.out.println(genericBeanDefinition);


        System.out.println(genericBeanDefinition.getBeanClassName());


    }
}