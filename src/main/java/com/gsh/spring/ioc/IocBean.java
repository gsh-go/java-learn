package com.gsh.spring.ioc;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 8:33
 * @Description:
 */
public class IocBean {
    public String id;
    public String className;

    public IocBean(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
