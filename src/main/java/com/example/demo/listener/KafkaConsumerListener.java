package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author: luozijian
 * @date: 5/6/21 11:52:33
 * @description:
 */
//@Component
@Slf4j
public class KafkaConsumerListener {

    /**
     * 设置自动提交offset为false
     * enable-auto-commit: false
     *
     * @param record
     * @param ack
     */
    // 消费监听
    @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record, Acknowledgment ack){
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
        // 手动提交offset
        ack.acknowledge();
    }

}