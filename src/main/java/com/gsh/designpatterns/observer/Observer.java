package com.gsh.designpatterns.observer;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/19 15:43
 * @Description: 抽象观察者
 */
public interface Observer {

    /**
     * 当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调
     * @param message
     */
     void update(String message);
}
