package com.java.intro.basicclass.lang;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
//        integerTest();
        Map<Character,Integer> map = new HashMap<>();
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> values = new ArrayList<>(map.values());
        System.out.println(list1.equals(values));
        String s = null;

    }

    private static void integerTest() {
        int i = 0;
        Integer inte = 10;//自动执行valueOf 完成了自动的装箱
        //自动装箱
        Integer integer = i;
        //自动拆箱
        i = inte.intValue();
        i = inte;//自动执行intValue
        //整数缓冲池
        //数据在-127~128之间不会调用new方法
        Integer a = Integer.valueOf(100);
        Integer b = Integer.valueOf(100);
        System.out.println(a == b);
        Integer c = new Integer(100);
        Integer d = new Integer(100);
        System.out.println(c == b);

        Integer integer1 = Integer.valueOf("5547");
        int integer2 = Integer.parseInt("2266");//用charAt()方法取每一位并计算的
        System.out.println(String.valueOf(integer1) + Integer.toString(integer2));
    }
}
