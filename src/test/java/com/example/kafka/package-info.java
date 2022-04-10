/**
 * @author: luozijian
 * @date: 5/6/21 15:05:09
 * @description:
 */
package com.example.kafka;

/**
 * 1.kafka监听端口接收连接请求流程：
 * Kafka.main-->kafkaServerStartable.startup-->KafkaServer.startup()-->
 * SocketServer.startup()-->SocketServer.Acceptor()-->Acceptor.run()-->
 * SocketServer.accept()-->newConnections.add(socketChannel)；把新连接添加到
 * newConnections队列中，然后等Processor线程poll取出注册到selector上
 *
 * 2.kafka处理消费端poll信息的请求流程
 * SocketServer.run()-->SocketServer.poll()-->Selector.poll()-->Selector.pollSelectionKeys()-->
 *
 *
 * 3.kafka生产者生产消息流程
 *
 * 首先在new KafkaProducer()创建生产者IO线程sender任务，sender任务初始化selector异步处理请求
 * Sender.run()-->NetworkClient.poll()-->Selector.poll()-->Selector.select()-->
 * nioSelector.select()
 *
 *
 * KafkaProducer.send()-->KafkaProducer.doSend()-->Serializer.serialize(key)-->
 * Serializer.serialize(value)-->KafkaProducer.partition()-->
 * RecordAccumulator.RecordAppendResult result = accumulator.append(tp, timestamp, serializedKey, serializedValue, interceptCallback, remainingWaitMs);-->
 * this.sender.wakeup();-->selector.select()-->
 * 备注：每个生产者都有一条io线程，这里唤醒的是该生产者的线程nioSelector
 *
 *
 * 4.kafka发送信息流程
 * NetworkClient.send()-->selector.send()-->KafkaChannel.setSend()-->
 * this.transportLayer.addInterestOps(SelectionKey.OP_WRITE);
 *
 * nioSelector线程捕捉到OP_WRITE后进行处理请求
 *
 * 5.生产者选择分区：
 * 没有指定key，计数器+1然后和分区总数取模
 * 有key，对key取一个hash值 % 分区的总数取模
 *
 *
 * 6.kafka获取元数据流程
 * 其实Kafak获取元数据的流程跟我们发送消息的流程是一模一样。
 * 获取元数据 -》 判断网络连接是否建立好 -》 建立网络连接
 * -》 发送请求（获取元数据的请求） -》 服务端发送回来响应（带了集群的元数据信息）
 *
 *
 * 7 生产者大概流程：
 * 1. new KafkaProducer()后创建一个后台线程KafkaThread扫描RecordAccumulator中是否有消息；
 * 2. 调用KafkaProducer.send()发送消息，实际上只是把消息保存到RecordAccumulator中；
 * 3. 后台线程KafkaThread扫描到RecordAccumulator中有消息后，将消息发送到kafka集群；
 * 4. 如果发送成功，那么返回成功；
 * 5. 如果发送失败，那么判断是否允许重试。如果不允许重试，那么返回失败的结果；如果允许重试，
 * 把消息再保存到RecordAccumulator中，等待后台线程KafkaThread扫描再次发送；
 *
 * 8.kafka消费者如何才能从头开始消费某个topic的全量
 * （1）使用一个全新的"group.id"（就是之前没有被任何消费者使用过）;
 * （2）指定"auto.offset.reset"参数的值为earliest；
 *
 *
 *
 * 9.batchs数据结构CopyOnWriteMap的多线程安全设计：
 * private volatile Map<K, V> map; volatile保证对map的修改立即对其他线程可见
 * get操作不加锁；
 * put操作加锁，修改完后map立即对其他线程的get操作可见；
 *
 *
 *
 */

