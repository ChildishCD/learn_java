package com.java.exercise.collection;

public class SE extends Worker{

    public SE(Integer ID, String name) {
        super(ID, name);
    }

    @Override
    public void show() {
        System.out.println("SE" + ID + ":" + name);
    }
}
