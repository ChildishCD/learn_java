package com.java.intro.basicclass.enumeration;

public class LoginInfo {
    private String name;
    private String pass;
    private String role;

    public final static LoginInfo SUPER_ADMIN = new LoginInfo("root","root","超级管理员");
    public final static LoginInfo ADMIN = new LoginInfo("admin","admin","管理员");

    // Private constructor!!
    private LoginInfo(String name, String pass, String role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
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

    public String getRole() {
        return role;
    }
}
