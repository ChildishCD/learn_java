package com.java.intro.basicclass.list;

import java.util.Objects;

public class One extends ManyOne implements Comparable<One>{
    private String name;
    private int sore;

    public One() {
    }

    public One(String name, int sore) {
        this.name = name;
        this.sore = sore;
    }

    public String getName() {
        return name;
    }

    public int getSore() {
        return sore;
    }

    //重写继承的抽象类方法
    @Override
    public void show() {
        System.out.println("First");
    }

    //重写判断相等的方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        One one = (One) o;
        return name.equals(one.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //按照分数排序
    @Override
    public int compareTo(One o) {
//        return this.sore - o.sore;//升序/正序
        return o.sore - this.sore;//降序/逆序
    }

    @Override
    public String toString() {
        return "One{" +
                "name='" + name + '\'' +
                ", sore=" + sore +
                '}';
    }
}
