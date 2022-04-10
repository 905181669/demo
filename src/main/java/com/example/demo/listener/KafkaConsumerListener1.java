package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: luozijian
 * @date: 5/6/21 11:52:33
 * @description:
 * 1.kafka存在consumer group的概念，也就是group.id一样的consumer，这些consumer属于一个consumer group，
 * 组内的所有消费者协调在一起来消费订阅主题的所有分区。当然每一个分区只能由同一个消费组内的consumer来消费
 *
 * 2.假设同一个消费组内有两个消费者，可以并发消费同一个topic下分区0和分区1的消息；
 * 3.假如同一个消费组内消费者数n>分区数m，那会有n-m个消费者无法消费到信息；
 *
 */
//@Component
@Slf4j
public class KafkaConsumerListener1 {


    /**
     * 演示1（3个partiton对应3个consumer）
     * Ø 创建一个带3个分区的topic
     * Ø 启动3个消费者消费同一个topic，并且这3个consumer属于同一个组
     * Ø 启动发送者进行消息发送
     * 演示结果：consumer1会消费partition0分区、consumer2会消费partition1分区、consumer3会消费partition2分区
     * 如果是2个consumer消费3个partition呢？会是怎么样的结果？
     *
     * 演示2（3个partiton对应2个consumer）
     * Ø 基于上面演示的案例的topic不变
     * Ø 启动2个消费这消费该topic
     * Ø 启动发送者进行消息发送
     * 演示结果：consumer1会消费partition0/partition1分区、consumer2会消费partition2分区
     *
     * 演示3（3个partition对应4个或以上consumer）
     * 演示结果：仍然只有3个consumer对应3个partition，其他的consumer无法消费消息
     *
     * 如果consumer从多个partition读到数据，不保证数据间的顺序性，kafka只保证在一个partition上数据是有序的，但多个partition，根据你读的顺序会有不同
     *
     * 链接：https://www.jianshu.com/p/e60e0cca0451
     *
     * @param record
     */
    @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

}