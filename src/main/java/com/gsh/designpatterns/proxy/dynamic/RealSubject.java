package com.gsh.designpatterns.proxy.dynamic;

/**
 * @Author: gsh
 * @Date: Created in 2018/7/11 16:01
 * @Description:
 */
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
