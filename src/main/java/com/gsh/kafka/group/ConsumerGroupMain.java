package com.gsh.kafka.group;

/**
 * @Author: gsh
 * @Date: Created in 2018/12/5 13:43
 * @Description:
 */
public class ConsumerGroupMain {
    public static void main(String[] args){
        String brokers = "Server2:9092";
        String groupId = "group01";
        String topic = "HelloWorld";
        int consumerNumber = 3;

        Thread producerThread = new Thread(new ProducerThread(brokers,topic));
        producerThread.start();

        ConsumerGroup consumerGroup = new ConsumerGroup(brokers,groupId,topic,consumerNumber);
        consumerGroup.start();
    }
}
