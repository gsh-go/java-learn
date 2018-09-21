package com.gsh.spring.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/21 8:36
 * @Description: 自定义注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface IocResource {

    //注解的name属性
    public String name() default "";
}
