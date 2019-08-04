package guk.matrix;

import guk.vector.Vector;

public class Matrix {
    private Vector[] matrixElements;

    public Matrix(int n, int m) {
        matrixElements = new Vector[n];
        for (int i = 0; i < n; i++) {
            matrixElements[i] = new Vector(m);
        }
    }

    public Matrix(double[][] array) {
        matrixElements = new Vector[array.length];
        int maxSize = findMaxNestedArraySize(array);
        for (int i = 0; i < array.length; i++) {
            matrixElements[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        matrixElements = new Vector[vectorsArray.length];
        int maxSize = findMaxVectorSize(vectorsArray);
        for (int i = 0; i < vectorsArray.length; i++) {
            matrixElements[i] = new Vector(maxSize);
            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                matrixElements[i].setComponentByIndex(vectorsArray[i].getComponentByIndex(j), j);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrixElements);
    }

    private static int findMaxVectorSize(Vector[] array) {
        int maxVectorSize = 0;
        for (Vector e : array) {
            maxVectorSize = (e.getSize() > maxVectorSize) ? e.getSize() : maxVectorSize;
        }
        return maxVectorSize;
    }

    private static int findMaxNestedArraySize(double[][] array) {
        int maxSize = 0;
        for (double[] e : array) {
            maxSize = (e.length > maxSize) ? e.length : maxSize;
        }
        return maxSize;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        for (int i = 0; i < matrixElements.length; i++) {
            s.append("{");

            for (int j = 0; j < matrixElements[i].getSize() - 1; j++) {
                s.append(matrixElements[i].getComponentByIndex(j)).append(", ");
            }
            s.append(matrixElements[i].getComponentByIndex(matrixElements[i].getSize() - 1)).append("}");

            if (i != matrixElements.length - 1) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }
//============================================================================================

    public int[] getSize() { //todo: а если матрица непрямоугольная?(например, после вставки вектора)
        return new int[]{matrixElements.length, matrixElements[0].getSize()};
    }

    public Vector getVectorByIndex(int index) {
        if (index >= matrixElements.length) {
            throw new IllegalArgumentException("Индекс должен быть < " + matrixElements.length);
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }

        return matrixElements[index];
    }

    public void setVectorByIndex(int index, Vector newVector) {
        if (index >= matrixElements.length) {
            throw new IllegalArgumentException("Индекс должен быть < " + matrixElements.length);
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }

        matrixElements[index] = newVector;
    }


    public Vector getVectorColumnByIndex(int index) { //todo: а если матрица непрямоугольная?(например, после вставки вектора)
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        if (index > getSize()[1] - 1) {
            throw new ArrayIndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }

        double[] array = new double[matrixElements.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = matrixElements[i].getComponentByIndex(index);
        }
        return new Vector(array);
    }
}

