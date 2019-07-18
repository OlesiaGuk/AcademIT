package guk.shapes;

import guk.shapesMain.UtilityFunctions;

public class Rectangle implements Shape {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideB() {
        return sideB;
    }

    @Override
    public double getWidth() {
        return sideA;
    }

    @Override
    public double getHeight() {
        return sideB;
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }

    @Override
    public double getPerimeter() {
        return (sideA + sideB) * 2;
    }

    @Override
    public String toString() {
        return "Прямоугольник со сторонами " + sideA + " и " + sideB + ". Площадь = " + UtilityFunctions.twoDecimalPlacesRound(getArea()) + ", периметр = " + UtilityFunctions.twoDecimalPlacesRound(getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return sideA == rectangle.sideA && sideB == rectangle.sideB;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideA);
        hash = prime * hash + Double.hashCode(sideB);
        return hash;
    }
}
