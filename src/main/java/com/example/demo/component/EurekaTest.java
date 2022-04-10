package com.example.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: luozijian
 * @date: 2020-10-22 10:47:59
 * @description:
 */
@Component
@Endpoint(id = "unregister")
@Slf4j
public class EurekaTest {


    @ReadOperation
    public String unregisterGet() {
        log.info("执行取消注册操作");
        return "成功从注册中心取消注册";
    }

    @WriteOperation
    public String unregisterPost(@Selector String confirm) {
        log.info("执行取消注册操作:"+confirm);
        if("yes".equals(confirm)){
            return "成功从注册中心取消注册";
        }
        return "";

    }


}


