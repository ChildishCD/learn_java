package com.java.pattern.decorator.topping;

import com.java.pattern.decorator.Drink;

//配料就是装饰者
public class Topping extends Drink {
    public Topping(Drink drink) {
        this.drink = drink;
    }

    protected Drink drink;
    @Override
    public Double sumPrice() {
        return drink.sumPrice() + super.getPrice();
    }

    @Override
    public String getName() {
        return drink.getName()+" & " + super.getName();
    }
}
