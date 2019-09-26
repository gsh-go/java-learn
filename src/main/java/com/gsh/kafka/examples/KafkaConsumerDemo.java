package com.gsh.kafka.examples;

public class KafkaConsumerDemo {

	public static final String KAFKASTR = "localhost:9092";

	public static void main(String[] args) {
		new Consumer(KAFKASTR, "topic","group_0").start(); // args[0] 为要接收的 topic
	}
}