package com.java.intro.generis;

public class Demo {
    public static void main(String[] args) {

    }

    private static <T, V, K> T shout(T t, V v, K k) {
        System.out.println(t + "!!!!!!!");
        System.out.println(v + "!!!!!!!");
        System.out.println(k + "!!!!!!!");
        return t;
    }
}
