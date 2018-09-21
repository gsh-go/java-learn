package com.gsh.spring.ioc;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 8:55
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        ClassPathXMLApplicationContext path = new ClassPathXMLApplicationContext("spring-ioc.xml");
        TestServiceImpl userService = (TestServiceImpl) path.getBean("testService");
        userService.show();
    }
}
