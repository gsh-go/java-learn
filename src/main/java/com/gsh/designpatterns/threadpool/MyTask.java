package com.gsh.designpatterns.threadpool;


/**
 * @Author: gsh
 * @Date: Created in 2018/6/14 9:42
 * @Description: 自定义任务
 */
public class MyTask implements Runnable {

    private int num;

    public MyTask(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + num);
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + num + "执行完毕");
    }




}
