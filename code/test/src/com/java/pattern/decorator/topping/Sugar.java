package com.java.pattern.decorator.topping;

import com.java.pattern.decorator.Drink;

public class Sugar extends Topping{
    public Sugar(Drink drink) {
        super(drink);
        setName("Sugar");
        setPrice(3.0);
    }
}
