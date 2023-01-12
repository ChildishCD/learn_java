package com.java.exercise.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Student {
    private String name;
    private Integer age;
    private Integer score;

    public Student(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getScore() {
        return score;
    }

    //随机生成名字
    public static String randomStudentName() {
        char[] lowAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random();
        String name = "";
        for (int i = 0; i < random.nextInt(10)+3 ; i++) {
            name = name + lowAlphabet[random.nextInt(lowAlphabet.length)];
        }
        return name;
    }

    //随机生成学生的list
    public static List createStudentList(int listLength) {
        List<Student> students = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (listLength > 0) {
            students.add(new Student(randomStudentName(), random.nextInt(0, 100), random.nextInt(0, 100)));
            listLength--;
        }
        return students;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
