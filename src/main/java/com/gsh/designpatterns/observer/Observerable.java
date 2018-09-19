package com.gsh.designpatterns.observer;


/**
 * @Author: gsh
 * @Date: Created in 2018/9/19 15:42
 * @Description: 抽象被观察者接口
 */
public interface Observerable {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}
