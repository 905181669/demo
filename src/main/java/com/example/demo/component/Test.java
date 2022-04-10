package com.example.demo.component;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: luozijian
 * @date: 2022/3/11
 * @description:
 */
@Component
public class Test {

    public Test() {
        System.out.println("在第11步：this.finishBeanFactoryInitialization(beanFactory)创建普通的bean");
    }
}