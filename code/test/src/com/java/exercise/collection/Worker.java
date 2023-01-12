package com.java.exercise.collection;

public abstract class  Worker {
    protected Integer ID;
    protected String name;

    public Worker(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public abstract void show();
}
