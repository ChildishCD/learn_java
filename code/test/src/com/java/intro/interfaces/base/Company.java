package com.java.intro.interfaces.base;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements IDetailInfo{
    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company() {
    }

    private String name;
    private String address;
    public Printer printer;//学校的打印机

    @Override
    public  String detail(){
        return "Co.Ltd " + name + " is located on " + address + ".";
    }

}
