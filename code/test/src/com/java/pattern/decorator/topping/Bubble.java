package com.java.pattern.decorator.topping;

import com.java.pattern.decorator.Drink;

public class Bubble extends Topping{
    public Bubble(Drink drink) {
        super(drink);
        setName("Bubble");
        setPrice(5.5);
    }
}
