package com.gsh.designpatterns.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: gsh
 * @Date: Created in 2018/7/11 16:01
 * @Description:
 */
public class RealSubject implements Subject {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealSubject.class);

    @Override
    public void rent() {
        LOGGER.info("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        LOGGER.info("hello: " + str);
    }
}
