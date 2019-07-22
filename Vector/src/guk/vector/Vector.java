package guk.vector;

import java.util.Arrays;

public class Vector {
    private double[] componentsArray;

    public Vector(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Размерность вектора должны быть > 0");
        componentsArray = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.componentsArray);
    }

    public Vector(double[] array) {
        componentsArray = new double[array.length];
        System.arraycopy(array, 0, componentsArray, 0, array.length);
    }

    public Vector(int n, double[] array) {
        componentsArray = new double[n];
        System.arraycopy(array, 0, componentsArray, 0, array.length);
    }

    public double[] getComponentsArray() {
        return componentsArray;
    }

    public void setComponentsArray(double[] componentsArray) {
        this.componentsArray = componentsArray;
    }

    public int getSize() {
        return componentsArray.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 0;
        s.append("{");
        while (i < componentsArray.length - 1) {
            s.append(componentsArray[i]).append(", ");
            i++;
        }
        s.append(componentsArray[i]).append("}");
        return s.toString();
    }

    public Vector getVectorsSum(Vector vector) {
        int minArraySize = Math.min(this.getSize(), vector.getSize());
        int maxArraySize = Math.max(this.getSize(), vector.getSize());

        double[] summaryArray = new double[maxArraySize];
        int i = 0;
        while (i < minArraySize) {
            summaryArray[i] = this.componentsArray[i] + vector.componentsArray[i];
            i++;
        }
        if (this.getSize() == maxArraySize) {
            System.arraycopy(this.componentsArray, i, summaryArray, i, summaryArray.length - i);
        } else {
            System.arraycopy(vector.componentsArray, i, summaryArray, i, summaryArray.length - i);
        }
        return new Vector(summaryArray);
    }

    public void getVectorScalarMultiply(double scalar) {
        for (int i = 0; i < this.getSize(); i++) {
            this.componentsArray[i] = this.componentsArray[i] * scalar;
        }
    }

    public void getVectorReversal() {
        this.getVectorScalarMultiply(-1);
    }

    public Vector getVectorsDifference(Vector vector) {
        vector.getVectorReversal();
        return getVectorsSum(vector);
    }

    public double getVectorLength() {
        double squaresSum = 0;
        for (int i = 0; i < this.getSize(); i++) {
            squaresSum += Math.pow(this.getComponentsArray()[i], 2);
        }
        return Math.sqrt(squaresSum);
    }

    public double getVectorComponentByIndex(int index) {
        return componentsArray[index];
    }

    public Vector setVectorComponentByIndex(double newComponent, int index) {
        if (index > componentsArray.length) {
            throw new IllegalArgumentException("Введенный индекс превышает размерность вектора");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        double[] newComponentsArray = new double[componentsArray.length + 1];
        int i = 0;
        while (i < index) {
            newComponentsArray[i] = componentsArray[i];
            i++;
        }
        int j = i;
        newComponentsArray[j] = newComponent;
        j++;
        while (j < newComponentsArray.length) {
            newComponentsArray[j] = componentsArray[i];
            i++;
            j++;
        }
        return new Vector(newComponentsArray);
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
        if (componentsArray.length != vector.componentsArray.length) {
            return false;
        }
        for (int i = 0; i < componentsArray.length - 1; i++) {
            if (componentsArray[i] != vector.componentsArray[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        return prime * hash + Arrays.hashCode(componentsArray);
    }

}
