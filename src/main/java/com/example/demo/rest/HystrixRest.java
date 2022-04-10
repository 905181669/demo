package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: luozijian
 * @date: 8/30/21 08:03:19
 * @description:
 */
@RestController
public class HystrixRest {

    // 模仿成功返回的接口
    @RequestMapping(value = "/successHello", method = RequestMethod.GET)
    public String successHello() {
        return "successHello";
    }

    // 模仿在请求的过程中，出现错误的接口
    @RequestMapping(value = "/errorHello", method = RequestMethod.GET)
    public String errorHello() throws Exception {
        Thread.sleep(10000);
        return "errorHello";
    }

}