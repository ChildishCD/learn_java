package com.java.exercise.collection;

public class PM extends Worker {
    public PM(Integer ID, String name) {
        super(ID, name);
    }

    @Override
    public void show() {
        System.out.println("PM" + ID + ":" + name);
    }
}
