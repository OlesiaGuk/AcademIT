package guk.shapes;

import java.util.Comparator;

public class ShapesPerimeterComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        if (shape1.getPerimeter() == shape2.getPerimeter()) {
            return 0;
        } else if (shape1.getPerimeter() > shape2.getPerimeter()) {
            return -1;
        } else {
            return 1;
        }
    }
}
