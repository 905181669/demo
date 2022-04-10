package com.example.demo.rest.resp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: luozijian
 * @date: 8/23/21 10:52:14
 * @description:
 */
@RestController
public class RibbonRest {

    @Autowired
    @Qualifier(value = "ribbonRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/ribbon")
    public String ribbon(){

        return restTemplate.getForObject("https://baidu.com", String.class);
    }
}