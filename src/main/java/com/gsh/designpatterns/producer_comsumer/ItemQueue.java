package com.gsh.designpatterns.producer_comsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 14:36
 * @Description:
 */
public class ItemQueue {
    private BlockingQueue<Item> queue;

    public ItemQueue() {

        queue = new LinkedBlockingQueue<>(10000);
    }

    public void put(Item item) throws InterruptedException {

        queue.put(item);
    }

    public Item take() throws InterruptedException {

        return queue.take();
    }
}
