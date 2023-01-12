package com.java.intro.interfaces.base;

public class Plotter extends Printer{

    @Override
    public void print(IDetailInfo detailInfo) {
        System.out.println(detailInfo.detail() + " [The Plotter is Processing...]");
    }
}
