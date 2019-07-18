package guk.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return Math.pow(sideLength, 2);
    }

    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return ("Квадрат со стороной " + sideLength + ". Площадь = " + (double) Math.round(getArea() * 100) / 100 + ", периметр = " + (double) Math.round(getPerimeter() * 100) / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        return prime * hash + Double.hashCode(sideLength);
    }
}
