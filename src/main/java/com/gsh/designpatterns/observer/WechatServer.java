package com.gsh.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/19 15:45
 * @Description: 被观察者
 */
public class WechatServer implements Observerable {

    private List<Observer> list;

    private String message;

    public WechatServer() {
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer o : list) {
            o.update(message);
        }
    }

    public void setInformation(String msg) {
        this.message = msg;
        System.out.println("服务更新消息:" + msg);
        notifyObserver();
    }
}
