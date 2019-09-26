package com.gsh.threadpool.cs.answer;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @Author: gsh
 * @Date: Created in 2018/11/9 16:14
 * @Description:
 */
public class ThreadTask {

    /**
     * 请求类型
     */
    private int type;

    private Selector selector;

    private SelectionKey key;

    public ThreadTask(int type, Selector selector, SelectionKey key) {
        this.type = type;
        this.selector = selector;
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public SelectionKey getKey() {
        return key;
    }

    public Selector getSelector() {
        return selector;
    }
}
