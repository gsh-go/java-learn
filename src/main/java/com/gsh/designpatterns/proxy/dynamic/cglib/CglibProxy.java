package com.gsh.designpatterns.proxy.dynamic.cglib;

import com.gsh.designpatterns.proxy.dynamic.jdk.DynamicProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: gsh
 * @Date: Created in 2019/9/26 10:49
 * @Description:
 */
public class CglibProxy implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CglibProxy.class);

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("++++++before " + method.getName() + "++++++");
        LOGGER.info(method.getName());
        Object o1 = methodProxy.invokeSuper(o, args);
        LOGGER.info("++++++after " + method.getName()+ "++++++");
        return o1;
    }
}
