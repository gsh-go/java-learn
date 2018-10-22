package com.gsh.designpatterns.producer_comsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 15:07
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        {
            ItemQueue queue = new ItemQueue();

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 1; i++) {

                final Producer producer = new Producer("Producer_" + i, queue, "D", 8366, 10000);
                executorService.submit(() -> {
                    while (true) {
                        producer.produce();
                    }
                });
            }

            for (int i = 0; i < 19; i++) {
                final Consumer consumer = new Consumer("Consumer_" + i, queue, "D");
                executorService.submit(() -> {
                    while (true) {
                        consumer.consume();
                    }
                });
            }
        }
    }
}
