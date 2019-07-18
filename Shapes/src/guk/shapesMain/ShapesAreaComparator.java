package guk.shapesMain;

import guk.shapes.Shape;

import java.util.Comparator;

public class ShapesAreaComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}