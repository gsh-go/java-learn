package com.gsh.designpatterns.proxy.dynamic.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: gsh
 * @Date: Created in 2018/7/11 16:02
 * @Description:
 */
public class DynamicProxy implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicProxy.class);

    /**
     * 这个就是我们要代理的真实对象
     */
    private Object subject;


    /**
     * 构造方法，给我们要代理的真实对象赋初值
     *
     * @param subject
     */
    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        LOGGER.info("before rent house");

        LOGGER.info("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        LOGGER.info("after rent house");
        return null;
    }

}
