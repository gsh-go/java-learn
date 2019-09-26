package com.gsh.kafka.examples;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 
* Title: Consumer
* Description: kafka消费者 
* Version:others.0.0
* @author pancm
* @date 2017年12月29日
 */
public class Consumer extends Thread {

	private final KafkaConsumer<String, String> consumer;
	private final String topic;

	public Consumer(String kafkaStr, String topic,String groupId) {
		Properties props = new Properties();
		props.put("bootstrap.servers", kafkaStr);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("max.poll.records", 1000);
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		this.consumer = new KafkaConsumer<>(props);
		this.topic = topic;
	}

	@Override
	public void run() {
		this.consumer.subscribe(Arrays.asList(topic));
		System.out.println("消费开始---------");
		try {
			while (true){
				ConsumerRecords<String,String> records = consumer.poll(100);
				for (ConsumerRecord<String,String> recode: records) {
					System.out.println("recodeOffset = " + recode.offset() + "recodeValue = " + recode.value());
				}
			}

		} finally {
			consumer.close();
		}
	}

}
