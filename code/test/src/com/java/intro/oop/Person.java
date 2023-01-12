package com.java.intro.oop;

import com.java.intro.annotation.Test1;
import com.java.intro.annotation.Test2;

@Test1("I'm a person")
@Test2
public class Person {

    //Public Private
    protected String name;
    protected Integer age;
    //private 不会被继承
    private String[] parents;

    public Integer grade;

    public Person() {
    }

    private Person(String name){
        this.name = name;
    }

    //构造方法
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private double getDied(String address,Double time){
        return age + time;
    }

    public String getParents() {
        return this.parents[0] + " " + this.parents[1];
    }

    public void setParents(String[] parents) {
        this.parents = parents;
    }

    //对于私有的属性可以用get和set方法对其赋值
    //体现封装的特征，对用户和程序工作者透明
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("invalid age value");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age + "}";
    }

    public void show() {
        System.out.println(this.toString());
    }
}

