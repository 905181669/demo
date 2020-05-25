package com.example.demo.rest;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: luozijian
 * @date: 2020-05-02 21:58:49
 * @description:
 */

@RestController
@RequestMapping("/test")
public class TestRest {

    @GetMapping("/get")
    public String get(HttpServletRequest request){


        Enumeration em = request.getHeaderNames();
        return "TEST";
    }
}
