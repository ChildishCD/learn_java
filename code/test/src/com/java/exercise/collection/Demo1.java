package com.java.exercise.collection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

public class Demo1 {
    public static void main(String[] args) {
//        test1();
//        System.out.println(getWeek("2022-12-31"));
//        test2();
        test3();
    }

    private static void test3() {
        //已知有十六支男子足球队参加2008 北京奥运会。写一个程序，把这16 支球队随机分为4 个组。
        // 采用List集合和随机数 2008 北京奥运会男足参赛国家：
        // 科特迪瓦,阿根廷,澳大利亚,塞尔维亚,荷兰,尼日利亚,日本,美国,中国,新西 兰,巴西,比利时,韩国,喀麦隆,洪都拉斯,意大利
        String input = "科特迪瓦,阿根廷,澳大利亚,塞尔维亚,荷兰,尼日利亚,日本,美国,中国,新西 兰,巴西,比利时,韩国,喀麦隆,洪都拉斯,意大利";
        List<String> nationList = new ArrayList<>();
        String[] nations = input.split(",");
        for (String s : nations) {
            nationList.add(s);
        }
        //多维list
        List<List<String>> groupList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Random random = new Random();
                int index = random.nextInt(nationList.size());
                temp.add(nationList.get(index));
                nationList.remove(index);
            }
            groupList.add(temp);
        }
        System.out.println(groupList);
    }

    private static void test2() {
        //创建Student类，属性包括id[1-40], score[0-100],所有属性随机生成。创建Set集合，保存20个对象，找到分数最高与最低的学生
        TreeSet<Student> set = new TreeSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(new Student());
        }
        System.out.println(set);
    }

    //基于集合，传入字符串时间，返回该时间是周几，中文显示（星期一）
    public static String getWeek(String time) {
        //判断传入参数是否为null或空
        if (time == null || time.isEmpty()) {
            return null;
            //抛出空指针异常
            //throw new NullPointerException("Please type in correct time!");
        }
        //将字符串转为时间类型
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(time, dateTimeFormatter);
        int week = localDate.get(ChronoField.DAY_OF_WEEK);
        Map<Integer, String> dayOfWeek = new HashMap<>();
        dayOfWeek.put(1, "星期一");
        dayOfWeek.put(2, "星期二");
        dayOfWeek.put(3, "星期三");
        dayOfWeek.put(4, "星期四");
        dayOfWeek.put(5, "星期五");
        dayOfWeek.put(6, "星期六");
        dayOfWeek.put(7, "星期日");
        return dayOfWeek.get(week);

    }

    private static void test1() {
        //创建一个HashMap对象，要求保存两个程序员对象和两个项目经理对象，使用员工的编号做为主键，并循环调用show的方法显示详细信息
        HashMap<Integer, Worker> map = new HashMap<>();
        SE xiaoming = new SE(1000, "小明");
        SE xiaohei = new SE(1001, "小黑");
        PM lilei = new PM(2000, "李雷");
        PM zhangsan = new PM(2001, "张三");
        map.put(xiaoming.getID(), xiaoming);
        map.put(xiaohei.getID(), xiaohei);
        map.put(lilei.getID(), lilei);
        map.put(zhangsan.getID(), zhangsan);

        map.forEach((k, v) -> {
            v.show();
        });

    }
}
