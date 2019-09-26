package com.gsh.designpatterns.proxy.dynamic.cglib;

import com.gsh.designpatterns.proxy.dynamic.RealSubject;
import com.gsh.designpatterns.proxy.dynamic.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: gsh
 * @Date: Created in 2019/9/26 10:51
 * @Description:
 */
public class App {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(cglibProxy);

        Subject o = (RealSubject) enhancer.create();
        o.hello("123");
        o.rent();
    }
}
