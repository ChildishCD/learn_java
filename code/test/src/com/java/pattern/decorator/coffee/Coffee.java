package com.java.pattern.decorator.coffee;

import com.java.pattern.decorator.Drink;

public class Coffee extends Drink {
    @Override
    public Double sumPrice() {
        return getPrice();
    }
}
