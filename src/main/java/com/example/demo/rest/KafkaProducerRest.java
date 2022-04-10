package com.example.demo.rest;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: luozijian
 * @date: 5/6/21 11:50:42
 * @description:
 */
@RestController
public class KafkaProducerRest {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private SpringUtil springUtil;

    // 发送消息
    @GetMapping("/kafka/send")
    public void sendMessage1(String msg) {
//        kafkaTemplate.send("topic1", msg);

        System.out.println(springUtil);
    }

}