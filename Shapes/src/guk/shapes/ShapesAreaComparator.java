package guk.shapes;

import java.util.Comparator;

public class ShapesAreaComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        if (Double.compare(shape1.getArea(), shape2.getArea()) == 0) {
            return 0;
        } else if (shape1.getArea() > shape2.getArea()) {
            return -1;
        } else {
            return 1;
        }
    }
}