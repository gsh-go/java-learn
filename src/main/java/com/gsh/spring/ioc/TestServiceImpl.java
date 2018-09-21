package com.gsh.spring.ioc;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 8:56
 * @Description:
 */
public class TestServiceImpl {

    private TestObject testObject;

    @IocResource(name = "testObject")
    public void setTestObject(TestObject testObject) {
        this.testObject = testObject;
    }

    public void show() {
        testObject.show();
        System.out.println("调用了TestService方法........");

    }
}
