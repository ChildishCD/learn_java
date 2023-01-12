package com.java.intro.oop;


import lombok.*;

//利用lombok自动生成类的各种方法
@Data//等于以下的方法
//@Getter
//@Setter
//@ToString//生成输出显示类表示方法（之前为默认方法）

@AllArgsConstructor
@NoArgsConstructor

public class Teacher extends Person{
    @NonNull//非空判断
    public Teacher(String name, int age) {
        super(name, age);
    }
}
