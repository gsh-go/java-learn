package com.gsh.kafka.examples;

public class KafkaProducerDemo {

	public static final String KAFKASTR = "localhost:9092";

	public static void main(String[] args) {
		new Producer(KAFKASTR, "topic").start(); // args[0] 为要发送的 topic
	}
}