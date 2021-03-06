package com.gsh.spring.ioc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 9:48
 * @Description:
 */
public class Bean {

    private String name;
    private String className;
    private String scope="singleton";
    private List<Property> properties=new ArrayList<Property>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
