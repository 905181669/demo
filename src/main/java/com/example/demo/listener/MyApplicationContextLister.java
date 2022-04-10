package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: luozijian
 * @date: 4/13/21 15:30:19
 * @description:
 */
@Slf4j
@Component
public class MyApplicationContextLister implements ApplicationListener<ApplicationEvent> {

    public MyApplicationContextLister() {
        log.info("MyApplicationContextLister creation");
    }

    //当容器中发布此事件以后，方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        log.info("收到事件："+event);
    }


}
