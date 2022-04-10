package com.example.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author: luozijian
 * @date: 2021/12/9 08:26:02
 * @description:
 * 3种方式定义BenDefinition，@Bean，@Component，@Import
 */
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);


        context.close();
    }

    public static class Config{

        @Bean
        public Car car(){
            return new Car();
        }
    }
}