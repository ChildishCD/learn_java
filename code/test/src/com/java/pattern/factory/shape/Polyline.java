package com.java.pattern.factory.shape;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Polyline implements Shape{
    @Override
    public void draw() {
        System.out.println("This is a polyline.");
    }
}
