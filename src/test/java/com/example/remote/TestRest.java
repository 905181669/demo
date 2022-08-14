package com.example.remote;

import com.example.demo.DemoApplication;
import com.example.demo.remote.EurekaRemote;
import com.example.demo.service.TestLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author: luozijian
 * @date: 2020-10-31 11:22:58
 * @description:
 */
@ActiveProfiles("uat")
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestRest {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public EurekaRemote eurekaRemote;

    @Test
    public void test(){

//        System.out.println(restTemplate.getForObject("http://eureka-service/test", String.class));

//        System.out.println(eurekaRemote.test());

        System.out.println(eurekaRemote.testHystrix("spring1"));
    }




}
