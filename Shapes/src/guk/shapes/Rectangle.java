package guk.shapes;

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

    public double getWidth() {
        return sideA;
    }

    public double getHeight() {
        return sideB;
    }

    public double getArea() {
        return sideA * sideB;
    }

    public double getPerimeter() {
        return (sideA + sideB) * 2;
    }

    @Override
    public String toString() {
        return ("Прямоугольник со сторонами " + sideA + " и " + sideB + ". Площадь = " + (double) Math.round(getArea() * 100) / 100 + ", периметр = " + (double) Math.round(getPerimeter() * 100) / 100);
    }
}
