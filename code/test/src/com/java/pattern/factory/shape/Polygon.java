package com.java.pattern.factory.shape;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Polygon implements Shape{

    @Override
    public void draw() {
        System.out.println("This is a polygon.");
    }
}
