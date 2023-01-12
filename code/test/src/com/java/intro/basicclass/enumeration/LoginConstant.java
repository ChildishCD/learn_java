package com.java.intro.basicclass.enumeration;

//利用接口用作常量的存放
//默认是用 public final static 修饰
//常量的命名规范，字母全部大写，不同单词用下划线连接
public interface LoginConstant {
    String SUPER_ADMIN_USERNAME = "root";
    String SUPER_ADMIN_PASSWORD = "root";
    String SUPER_ADMIN_ROLE = "超级管理员";

    String ADMIN_USERNAME = "admin";
    String ADMIN_PASSWORD = "123456";
    String ADMIN_ROLE = "管理员";

    String TEST_USERNAME = "test";
    String TEST_PASSWORD = "test";
    String TEST_ROLE = "测试";
}
