package com.gsh.threadpool.cs.answer;

import java.io.IOException;

/**
 * @Author: gsh
 * @Date: Created in 2018/11/9 17:08
 * @Description:
 */
public interface IThreadCallBack {

    /**
     *
     * @param task
     */
    void doTask(ThreadTask task) throws IOException;
}
