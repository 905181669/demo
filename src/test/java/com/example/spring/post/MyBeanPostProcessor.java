package com.example.spring.post;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author: luozijian
 * @date: 4/12/21 20:30:29
 * @description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * 注意：方法返回值不能为null
     * 如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
     * 因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 before--实例化的bean对象:"+bean+"\t"+beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }

    /**
     * 实例化、依赖注入、初始化完毕时执行
     * 注意：方法返回值不能为null
     * 如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
     * 因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 after...实例化的bean对象:"+bean+"\t"+beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }

    /**
     * postProcessBeforeInitialization方法的输出语句是在Bean实例化及属性注入后执行的，
     * 且在自定义的初始化方法之前执行(通过init-method指定)。
     * 而postProcessAfterInitialization方法是在自定义初始化方法执行之后执行的。
     *
     */

}
