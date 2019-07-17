package guk.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getWidth() {
        double max = getMax(x1, x2, x3);
        double min = getMin(x1, x2, x3);

        return max - min;
    }

    public double getHeight() {
        double max = getMax(y1, y2, y3);
        double min = getMax(y1, y2, y3);

        return max - min;
    }

    public double getArea() {
        double sideABLength = getTriangleSideLength(x1, y1, x2, y2);
        double sideBCLength = getTriangleSideLength(x2, y2, x3, y3);
        double sideACLength = getTriangleSideLength(x1, y1, x3, y3);
        double triangleHalfPerimeter = (sideABLength + sideBCLength + sideACLength) / 2;

        return Math.sqrt(triangleHalfPerimeter * (triangleHalfPerimeter - sideABLength) * (triangleHalfPerimeter - sideBCLength) * (triangleHalfPerimeter - sideACLength));
    }

    public double getPerimeter() {
        double sideABLength = getTriangleSideLength(x1, y1, x2, y2);
        double sideBCLength = getTriangleSideLength(x2, y2, x3, y3);
        double sideACLength = getTriangleSideLength(x1, y1, x3, y3);

        return sideABLength + sideBCLength + sideACLength;
    }

    private static double getTriangleSideLength(double a1, double b1, double a2, double b2) {
        return Math.sqrt(Math.pow((a2 - a1), 2) + Math.pow((b2 - b1), 2));
    }

    private static double getMax(double a, double b, double c) {
        if (a > b) {
            if (a > c) {
                return a;
            }
        } else if (b > c) {
            return b;
        }
        return c;
    }

    private static double getMin(double a, double b, double c) {
        if (a < b) {
            if (a < c) {
                return a;
            }
        } else if (b < c) {
            return b;
        }
        return c;
    }

    @Override
    public String toString() {
        String x1y1 = "(" + x1 + ", " + y1 + ")";
        String x2y2 = "(" + x2 + ", " + y2 + ")";
        String x3y3 = "(" + x3 + ", " + y3 + ")";
        return ("Треугольник с вершинами " + x1y1 + x2y2 + x3y3 + ". Площадь = " + (double) Math.round(getArea() * 100) / 100 + ", периметр = " + (double) Math.round(getPerimeter() * 100) / 100);
    }
}
