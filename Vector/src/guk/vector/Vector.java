package guk.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должны быть > 0");
        }
        components = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.components);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должны быть > 0");
        }
        components = new double[array.length];
        System.arraycopy(array, 0, components, 0, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должны быть > 0");
        }
        components = new double[n];
        System.arraycopy(array, 0, components, 0, array.length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        for (int i = 0; i < components.length - 1; i++) {
            s.append(components[i]).append(", ");
        }
        s.append(components[components.length - 1]).append("}");
        return s.toString();
    }

    public void add(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        } else if (getSize() > vector.getSize()) {
            vector.components = Arrays.copyOf(vector.components, getSize());
        }
        for (int i = 0; i < getSize(); i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        } else if (getSize() > vector.getSize()) {
            vector.components = Arrays.copyOf(vector.components, getSize());
        }
        for (int i = 0; i < getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;
        for (double e : components) {
            squaresSum += Math.pow(e, 2);
        }
        return Math.sqrt(squaresSum);
    }

    public double getComponentByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        if (index > components.length) {
            throw new ArrayIndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }
        return components[index];
    }

    public void setComponentByIndex(double newComponent, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        if (index > components.length) {
            throw new ArrayIndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }
        components = Arrays.copyOf(components, getSize() + 1);
        for (int i = getSize() - 1; i > index; i--) {
            components[i] = components[i - 1];
        }
        components[index] = newComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        if (components.length != vector.components.length) {
            return false;
        }
        for (int i = 0; i < components.length - 1; i++) {
            if (components[i] != vector.components[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        return prime * hash + Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int minArraySize = Math.min(vector1.getSize(), vector2.getSize());
        int maxArraySize = Math.max(vector1.getSize(), vector2.getSize());

        double[] summaryArray = new double[maxArraySize];
        int i = 0;
        while (i < minArraySize) {
            summaryArray[i] = vector1.components[i] + vector2.components[i];
            i++;
        }
        if (vector1.getSize() == maxArraySize) {
            System.arraycopy(vector1.components, i, summaryArray, i, summaryArray.length - i);
        } else {
            System.arraycopy(vector2.components, i, summaryArray, i, summaryArray.length - i);
        }
        return new Vector(summaryArray);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int minArraySize = Math.min(vector1.getSize(), vector2.getSize());
        int maxArraySize = Math.max(vector1.getSize(), vector2.getSize());

        double[] summaryArray = new double[maxArraySize];
        int i = 0;
        while (i < minArraySize) {
            summaryArray[i] = vector1.components[i] - vector2.components[i];
            i++;
        }
        if (vector1.getSize() == maxArraySize) {
            System.arraycopy(vector1.components, i, summaryArray, i, summaryArray.length - i);
        } else {
            System.arraycopy(vector2.components, i, summaryArray, i, summaryArray.length - i);
        }
        return new Vector(summaryArray);
    }

    public static Vector getScalarMultiplication(Vector vector1, Vector vector2) {
        int minArraySize = Math.min(vector1.getSize(), vector2.getSize());
        int maxArraySize = Math.max(vector1.getSize(), vector2.getSize());

        double[] summaryArray = new double[maxArraySize];
        int i = 0;
        while (i < minArraySize) {
            summaryArray[i] = vector1.components[i] * vector2.components[i];
            i++;
        }
        if (vector1.getSize() == maxArraySize) {
            System.arraycopy(vector1.components, i, summaryArray, i, summaryArray.length - i);
        } else {
            System.arraycopy(vector2.components, i, summaryArray, i, summaryArray.length - i);
        }
        return new Vector(summaryArray);
    }
}