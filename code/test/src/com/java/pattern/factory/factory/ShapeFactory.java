package com.java.pattern.factory.factory;

import com.java.pattern.factory.color.Color;
import com.java.pattern.factory.shape.Point;
import com.java.pattern.factory.shape.Polygon;
import com.java.pattern.factory.shape.Polyline;
import com.java.pattern.factory.shape.Shape;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    //create getShape() method to return a prescribed shape
    public Shape getShape(String shapeType) {
        if (shapeType == null || shapeType.isEmpty()) {
            return null;
        }
        switch (shapeType) {
            case "Point":
                return new Point();
            case "Polygon":
                return new Polygon();
            case "Polyline":
                return new Polyline();
        }
        return null;
    }
}
