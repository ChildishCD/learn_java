package com.java.pattern.factory.color;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class White implements Color{
    @Override
    public void fill() {
        System.out.println("White filled.");
    }
}
