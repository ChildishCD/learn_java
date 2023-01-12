package com.java.intro.io;

import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String pass;
    private Integer age;

    public User(Integer integer){};

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User(Integer id, String name, String pass, Integer age) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.join("-",id.toString(),name,pass,age.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && pass.equals(user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass);
    }
}
