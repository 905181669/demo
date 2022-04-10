package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: luozijian
 * @date: 5/21/21 11:07:20
 * @description:
 */
public class MyConsumerTest {

    public static final String brokerList="localhost:9092";

    public static final String topic="topic1";


    /**
     * （1）使用一个全新的"group.id"（就是之前没有被任何消费者使用过）;
     * （2）指定"auto.offset.reset"参数的值为earliest；
     * @param args
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        // 用户拉取kafka的元数据
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", "myConsumer1");
        props.put("auto.offset.reset", "earliest");
        //创建一个消费者客户端实例
        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(props);
        //订阅主题
        consumer.subscribe(Arrays.asList(topic, "test"));

        System.out.println("消费者开始消费...");
        //循环消费消息
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(1000);
            for (ConsumerRecord<String,String> record:records){
                System.out.println("消费者-收到消息: " + record.value());
            }
        }

    }
}