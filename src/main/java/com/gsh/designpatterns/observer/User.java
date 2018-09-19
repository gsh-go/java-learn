package com.gsh.designpatterns.observer;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/19 16:00
 * @Description: 观察者
 */
public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }
}
