package com.java.intro.basicclass.enumeration;

public enum LoginInfoEnum {
    SUPER_ADMIN("root","root","超级管理员"),
    ADMIN("admin","admin","管理员"),
    VISITOR("hello","world","游客")
    ;
    private LoginInfoEnum(String name, String pass, String role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
    }
    private String name;
    private String pass;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
