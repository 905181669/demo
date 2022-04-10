package com.example.spring.context;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: luozijian
 * @date: 4/12/21 21:24:50
 * @description:
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String... configurations){
        super(configurations);
    }

    @Override
    protected void initPropertySources(){
        getEnvironment().setRequiredProperties("VAR");
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("spring中并没有具体去实现postProcessBeanFactory方法，是提供给想要实现BeanPostProcessor的三方框架使用的。谁要使用谁就去实现");

    }
}
