package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: luozijian
 * @date: 2020-05-02 21:58:49
 * @description:
 */

@RestController
@RequestMapping("/test")
public class TestRest {

    @GetMapping("/get")
    public String get(){

        return "测试";
    }
}
