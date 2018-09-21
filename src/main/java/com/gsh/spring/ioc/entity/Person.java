package com.gsh.spring.ioc.entity;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 9:47
 * @Description:
 */
public class Person {

    private Student student;
    private Teacher teacher;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
