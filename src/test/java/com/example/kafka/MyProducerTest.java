package com.example.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


/**
 * @author: luozijian
 * @date: 5/8/21 14:52:09
 * @description:
 */
public class MyProducerTest{


    public static void main(String[] args) throws Exception{
        MyProducer myProducer = new MyProducer("topic1", false);
        myProducer.start();
        myProducer.join();

//        Thread.sleep(100000);

    }

}


class MyProducer extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Boolean isAsync;

    /**
     * 构建方法，初始化生产者对象
     * @param topic
     * @param isAsync
     */
    public MyProducer(String topic, Boolean isAsync) {
        Properties props = new Properties();
        // 用户拉取kafka的元数据
        props.put("bootstrap.servers", "localhost:9092");
        //
        props.put("client.id", "DemoProducer");
        //设置 序列化的类。
        // 二进制的格式
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("max.request.size", 100000);
        props.put("buffer.memory", 33554432); //发送消息的缓冲区，默认值是33554432=32M
        props.put("compression.type", "lz4");
        props.put("batch.size", 16384); //如果batch太大，会导致一条消息需要等待很久才能被发送出去，而且会让内存缓冲区有很大压力，过多数据缓冲在内存里，默认值是：16384，就是16kb，




        //消费者，消费数据的时候，就需要进行反序列化。
        //对象
        // 初始化kafkaProducer
        producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public void run() {

        // 一直会往kafka发送数据
        for(int messageNo = 0; messageNo < 1; messageNo ++) {

            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            //isAsync , kafka发送数据的时候，有两种方式
            //1: 异步发送
            //2: 同步发送
            //isAsync: true的时候是异步发送，false就是同步发送
            if (isAsync) { // Send asynchronously
                //异步发送
                //这样的方式，性能比较好，我们生产代码用的就是这种方式。
                producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        messageStr), new DemoCallBack(startTime, messageNo, messageStr));
            } else { // Send synchronously
                try {
                    //同步发送
                    //发送一条消息，等这条消息所有的后续工作都完成以后才继续下一条消息的发送。
                    producer.send(new ProducerRecord<>(topic,0,
                            messageNo,
                            messageStr)).get();
                    System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            try{
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


class DemoCallBack implements Callback {


    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. Exactly one of the arguments will be
     * non-null.
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if(exception != null){
            System.out.println("有异常");
            //一般我们生产里面 还会有其它的备用的链路。
        }else{
            System.out.println("说明没有异常信息，成功的！！");
        }
        if (metadata != null) {
            System.out.println("线程-" + Thread.currentThread().getName() +
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
