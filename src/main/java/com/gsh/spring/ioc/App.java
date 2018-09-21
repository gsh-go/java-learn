package com.gsh.spring.ioc;

import com.gsh.spring.ioc.entity.Person;
import com.gsh.spring.ioc.entity.Student;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 9:59
 * @Description:
 */
public class App {

    public static void main(String[] args) {
        BeanFactory bf=new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person s=(Person)bf.getBean("person");
        Person s1=(Person)bf.getBean("person");
        System.out.println(s==s1);
        System.out.println(s1);
        Student stu1=(Student) bf.getBean("student");
        Student stu2=(Student) bf.getBean("student");
        String name=stu1.getName();
        System.out.println(name);
        System.out.println(stu1==stu2);
    }
}
