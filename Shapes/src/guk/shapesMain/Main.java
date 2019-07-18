package guk.shapesMain;

import guk.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapesArray = new Shape[5];
        shapesArray[0] = new Square(15);
        shapesArray[1] = new Square(20);
        shapesArray[2] = new Rectangle(5, 25);
        shapesArray[3] = new Triangle(1, 1, 2, 5, 5, 3);
        shapesArray[4] = new Circle(7);

        System.out.println("Исходный список фигур:");
        for (Shape sh : shapesArray) {
            System.out.println(sh);
        }

        ShapesAreaComparator shapesAreaComparator = new ShapesAreaComparator();
        Arrays.sort(shapesArray, shapesAreaComparator);

        System.out.println(System.lineSeparator() + "Фигура с максимальной площадью: ");
        for (Shape sh : shapesArray) {
            if (sh.getArea() == shapesArray[0].getArea()) {
                System.out.println(sh);
            }
        }

        ShapesPerimeterComparator shapesPerimeterComparator = new ShapesPerimeterComparator();
        Arrays.sort(shapesArray, shapesPerimeterComparator);

        System.out.println(System.lineSeparator() + "Фигура со вторым по величине периметром: ");
        Shape secondMaxPerimeterShape = getSecondMaxPerimeterShape(shapesArray);
        if (secondMaxPerimeterShape == null) {
            System.out.println("Фигура со вторым по величине периметром отсутствует в списке");
        } else {
            for (Shape sh : shapesArray) {
                if (sh.getPerimeter() == secondMaxPerimeterShape.getPerimeter()) {
                    System.out.println(sh);
                }
            }
        }
    }

    private static Shape getSecondMaxPerimeterShape(Shape[] shapesArray) {
        double max = shapesArray[0].getPerimeter();
        for (int i = 1; i < shapesArray.length; i++) {
            if (shapesArray[i].getPerimeter() < max) {
                return shapesArray[i];
            }
        }
        return null;
    }
}