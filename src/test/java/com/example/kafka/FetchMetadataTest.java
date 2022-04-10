package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * @author: luozijian
 * @date: 5/12/21 09:11:28
 * @description:
 */
public class FetchMetadataTest {

    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        // 用户拉取kafka的元数据
        props.put("bootstrap.servers", "localhost:9092");
        //
        props.put("client.id", "DemoProducer");
        //设置 序列化的类。
        // 二进制的格式
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //消费者，消费数据的时候，就需要进行反序列化。
        //对象
        // 初始化kafkaProducer
        KafkaProducer producer = new KafkaProducer<>(props);

        String topic = "topic1";

        while (true){
            Thread.sleep(1000);


            /**
             * 5秒后获取topic的元数据
             */
            Thread.sleep(2000);


        }

        /**
         * waitOnMetadata流程：
         * 1.把需要更新元数据的topic加到topics集合中
         * 2.调用requestUpdate()设置需要更新状态为true
         * 3.wakeup selector
         * 4.selector线程发起请求更新元数据，代码在DefaultMetadataUpdater.maybeUpdate()
         */

    }
}