package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: luozijian
 * @date: 2021/12/8 18:00:29
 * @description:
 */

@Component
@ConditionalOnClass(name = "com.example.demo.component.MyServletContext1")
public class ConditionTest {


    public ConditionTest(){
        System.out.println("ConditionTest...");
    }
}