package com.java.intro.oop;

public class Student extends Person {
    private int exp;


    //继承父类 Person
    //子类不能访问父类的private属性
    //严禁定义与父类重名的字段
    //将父类的private更改为protected即可
    public Student(String name, int age, int exp) {
        //子类构造方法必须第一行构造父类,即祖宗优先
        super(name, age);//自动调用父类的构造方法
//        super.age//调用父类的属性
        this.exp = exp;
    }

    //super关键字表示父类（超类）。子类引用父类的字段时，可以用super.fieldName
    public String hello() {
        return "Hello, " + super.name;
    }

    //在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）
    //Override和Overload不同的是，
    // 如果传参不同，就是Overload，Overload方法是一个新方法；
    // 如果传参相同，并且返回值也相同，就是Override
    @Override
    public void setName(String name) {
        super.setName(name + "（学）");
    }
    //多态
    //java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型,
    //Person p = new Student();
    //p.run(); // 无法确定运行时究竟调用哪个run()方法


    @Override
    public String toString() {
        return "Student{" +
                "exp=" + exp +
                ", name='" + name + '\'' +
                ", age=" + age +
                "}学";
    }

    @Override
    public void show() {
        System.out.println(this.toString());;
    }
}
