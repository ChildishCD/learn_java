package com.java.intro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test1 {
    //要求使用这个注解的时候给一个value值
    //利用的时候 @AnnotationTest("") 什么属性也不写,默认给value()方法
    String value();
}
