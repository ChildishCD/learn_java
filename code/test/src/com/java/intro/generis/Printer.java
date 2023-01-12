package com.java.intro.generis;

import com.java.intro.oop.Animal;

import java.io.Serializable;
import java.util.List;

public class Printer <T extends Animal & Serializable> {
    private T thingToPrint;

    public Printer(T thingToPrint) {
        this.thingToPrint = thingToPrint;
    }

    public void print(){
        thingToPrint.bodyAge();
        System.out.println(thingToPrint);
    }

    public void print(List<? extends Animal> list){

    }

}
