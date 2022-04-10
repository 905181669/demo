package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: luozijian
 * @date: 8/23/21 10:51:43
 * @description:
 */

@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    RestTemplate ribbonRestTemplate() {
        return new RestTemplate();
    }

}

