package com.java.pattern.decorator;

public abstract class Drink {
    private String name;
    private double price;

    //calculate the price
    public abstract Double sumPrice();

    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
