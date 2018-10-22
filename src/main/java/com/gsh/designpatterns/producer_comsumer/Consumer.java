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
    //   private static String FILEPATH = "E:\\image_C\\";

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final ItemQueue queue;

    private final String name;

    private String type;

    public Consumer(String name, ItemQueue queue, String type) {
        this.name = name;
        this.queue = queue;
        this.type = type;
    }

    public void consume() throws InterruptedException {
        String filePath = "E:\\image_" + type + "\\";
        Item item = queue.take();
        ImageDownload download = new ImageDownload();
        download.downloadPicture(URI + item.getPath(), filePath + item.getUserId() + "\\" + item.getPicuterId() + ".jpg");
        LOGGER.info("消费者 [{}] 正在下载RUNNER [{}] 的照片 [{}]", name, item.getUserId(), item.getPicuterId());

    }
}
