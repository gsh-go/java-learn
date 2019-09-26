package com.gsh.kafka.examples;

public class KafkaConsumerDemo3 {

	public static final String KAFKASTR = "localhost:9092";

	public static void main(String[] args) {
		new Consumer(KAFKASTR, "topic_1","gtoup_3").start(); // args[0] 为要接收的 topic
	}
}