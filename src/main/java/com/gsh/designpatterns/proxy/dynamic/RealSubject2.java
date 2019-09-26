package com.gsh.designpatterns.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: gsh
 * @Date: Created in 2018/7/11 16:01
 * @Description:
 */
public class RealSubject2 implements Subject {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealSubject2.class);

    @Override
    public void rent() {
        LOGGER.info("I want to rent my house----2");
    }

    @Override
    public void hello(String str) {
        LOGGER.info("hello----2: " + str);
    }
}
