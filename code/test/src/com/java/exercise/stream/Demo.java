package com.java.exercise.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
//        test1()
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        //javawwwaababcabcdabcde 统计字符串中每个字母出现的次数，要求结果j(1)  v(1) w(3)  a(7) b(4) c(3)
        String str = "javawwwaababcabcdabcde";
        List<String> list = Arrays.asList(str.split(""));
        //利用stream
        Map<String,Long> collect1 = list.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(collect1);
        //利用merge
        Map<String, Long> collect2 = new HashMap<>();
        for (String s : list) {
            collect2.merge(s, 1L, Long::sum);
        }
        System.out.println(collect2);
    }

    private static void test3() {
        //把如下元素存入List集合 “aaa” “bbb” “aaa” “abc”“xyz” “123” “xyz” 去掉重复元素
        List<String> list = Arrays.asList("aaa","bbb","aaa","abc","xyz","123","xyz");
        List<String> collect = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void test2() {
        // 第一个数组内容为：[黑龙江省,浙江省,江西省,广东省,福建省]
        // 第二个数组为：[哈尔滨,杭州,南昌,广州,福州]
        // 将第一个数组元素作为key，第二个数组元素作为value存储到Map集合中。如{黑龙江省=哈尔滨, 浙江省=杭州, …}
        String provinceStr = "黑龙江省,浙江省,江西省,广东省,福建省";
        String cityStr = "哈尔滨,杭州,南昌,广州,福州";
        String[] province = provinceStr.split(",");
        String[] city = cityStr.split(",");
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < city.length; i++){
            map.put(province[i],city[i]);
        }
        System.out.println(map);
    }

    private static void test1() {
        //使用List集合存储10个学生信息。学生信息：姓名，年龄，成绩。统计所有姓“张”的同学的平均成绩
        List<Student> students = Student.createStudentList(10);
        //流的结果为浮点数
        Double collect = students.stream()
                .filter(student -> student.getName().contains("z"))
                .collect(Collectors.averagingInt(Student::getScore));
        System.out.println(collect);
    }
}
