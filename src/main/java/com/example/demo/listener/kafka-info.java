/**
 * @author: luozijian
 * @date: 5/7/21 17:47:35
 * @description:
 */
package com.example.demo.listener;

/**
 * 1.kafka client启动流程
 *
 * KafkaAutoConfiguration-->KafkaMessageListenerContainer.ListenerConsumer()-->start()-->run()-->
 * KafkaMessageListenerContainer.pollAndInvoke()-->KafkaConsumer.poll()-->
 * KafkaConsumer.pollForFetches()-->Fetcher.fetchedRecords()-->
 * Fetcher.fetchRecords(Fetcher<K, V>.PartitionRecords partitionRecords, int maxRecords)-->
 * Fetcher.fetchRecords(int maxRecords)-->Fetcher.nextFetchedRecord()-->
 *
 *
 *
 *
 *
 */