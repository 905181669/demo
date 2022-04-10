package com.example.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author: luozijian
 * @date: 5/3/21 17:19:19
 * @description: 每个web项目只有一个ServletContext，所以request共享
 */
@Component
public class MyServletContext implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {

        //ApplicationContextFacade
        this.servletContext = servletContext;

        servletContext.setAttribute("luozijian", "测试ServletContext");
    }


}