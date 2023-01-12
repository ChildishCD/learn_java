package com.java.exercise.collection;

import java.util.concurrent.ThreadLocalRandom;

public class Student implements Comparable<Student>{
    private Integer id;
    private String name;
    private Integer score;

    public Student() {
        this.name = name;
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        this.id = localRandom.nextInt(1,41);
        this.score = localRandom.nextInt(0,101);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        return this.score - o.score;
    }
}
