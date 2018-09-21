package com.gsh.spring.ioc.entity;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 9:47
 * @Description:
 */
public class Teacher {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
