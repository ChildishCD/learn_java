package com.java.pattern.factory.factory;

import com.java.pattern.factory.color.Black;
import com.java.pattern.factory.color.Color;
import com.java.pattern.factory.color.White;
import com.java.pattern.factory.shape.Shape;

public class ColorFactory extends AbstractFactory{
    @Override
    public Shape getShape(String color) {
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        if (colorType == null || colorType.isEmpty()) {
            return null;
        }
        switch (colorType) {
            case "Black":
                return new Black();
            case "White":
                return new White();
        }
        return null;
    }
}
