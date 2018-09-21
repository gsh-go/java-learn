package com.gsh.spring.ioc;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 9:58
 * @Description:
 */
public interface BeanFactory {
    //核心方法getBean
    Object getBean(String name);
}
