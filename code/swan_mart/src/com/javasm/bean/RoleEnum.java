package com.javasm.bean;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("管理员","admin"),
    CASHIER("收银员","123")
    ;

    private String roleName;
    private String passWord;

    RoleEnum(String name, String pass) {
        this.roleName = name;
        this.passWord = pass;
    }
}
