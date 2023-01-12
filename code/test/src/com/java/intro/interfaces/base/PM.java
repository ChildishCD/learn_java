package com.java.intro.interfaces.base;

import lombok.*;

@Data
public class PM implements IDetailInfo{
    public PM(String name, int exp) {
        this.name = name;
        this.exp = exp;
    }
    @Override
    public String toString() {
        return "PM{" +
                "name='" + name + '\'' +
                ", exp=" + exp +
                '}';
    }
    private String name;
    private int exp;

    @Override
    public  String detail(){
        return "Project Manager " + name + " has " + exp +" years experience.";
    }
}
