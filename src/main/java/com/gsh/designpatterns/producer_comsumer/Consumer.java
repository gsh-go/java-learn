package com.gsh.designpatterns.producer_comsumer;

import com.gsh.http.ImageDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/19 14:55
 * @Description:
 */
public class Consumer {
    private static String URI = "http://cdn.pic.runff.com";
    private static String FILEPATH = "E:\\image_2\\";

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final ItemQueue queue;

    private final String name;
    public Consumer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void consume() throws InterruptedException {

        Item item = queue.take();
        ImageDownload download = new ImageDownload();
        download.downloadPicture(URI + item.getPath(), FILEPATH + item.getUserId() + "\\" + item.getPicuterId() + ".jpg");
        LOGGER.info("消费者 [{}] 正在下载RUNNER [{}] 的照片 [{}]", name, item.getUserId(), item.getPicuterId());

    }
}
