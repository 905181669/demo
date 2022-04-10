package com.example.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: luozijian
 * @date: 4/13/21 14:40:27
 * @description:
 */
public class TestBean implements InitializingBean, DisposableBean, ApplicationContextAware, BeanNameAware {

    private String name;

    public TestBean(String name) {
        System.out.println("-------------construct--------------");
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("-------------set method--------------");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------InitializingBean#afterPropertiesSet-----");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("---------DisposableBean#destroy--------------");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("---------BeanNameAware#setApplicationContext----");
    }


    @Override
    public void setBeanName(String s) {
        System.out.println("----------ApplicationContextAware#setBeanName----");
    }

    public void initFromBean(){
        System.out.println("----------initFrom@Bean----------");
    }

    public void destroyFromBean(){
        System.out.println("----------destroyFrom@Bean---------------");
    }

    @PostConstruct
    public void customInit()
    {
        System.out.println("---------@PostConstruct----------");
    }

    @PreDestroy
    public void customDestroy()
    {
        System.out.println("---------@PreDestroy--------");
    }
}

