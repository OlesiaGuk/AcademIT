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
            maxVectorSize = Math.max(e.getSize(), maxVectorSize);
        }
        return maxVectorSize;
    }

    private static int findMaxNestedArraySize(double[][] array) {
        int maxSize = 0;
        for (double[] e : array) {
            maxSize = Math.max(e.length, maxSize);
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

    public int[] getSize() {
        return new int[]{matrixElements.length, matrixElements[0].getSize()};
    }

    public Vector getVectorByIndex(int index) {
        if (index >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + matrixElements.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }

        return matrixElements[index];
    }

    public void setVectorByIndex(int index, Vector newVector) {
        if (index >= matrixElements.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + matrixElements.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (newVector.getSize() != getSize()[1]) {
            throw new IllegalArgumentException("Размерность переданного вектора не соответствует размерности векторов в матрице");
        }

        matrixElements[index] = newVector;
    }

    public Vector getVectorColumnByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index > getSize()[1] - 1) {
            throw new IndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }

        double[] array = new double[matrixElements.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = matrixElements[i].getComponentByIndex(index);
        }
        return new Vector(array);
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getSize()[0]; i++) {
            matrixElements[i].multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        if (getSize()[0] != matrix.getSize()[0] || getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }
        for (int i = 0; i < getSize()[0]; i++) {
            matrixElements[i].add(matrix.matrixElements[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getSize()[0] != matrix.getSize()[0] || getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }
        for (int i = 0; i < getSize()[0]; i++) {
            matrixElements[i].subtract(matrix.matrixElements[i]);
        }
    }

    public void transpose() {
        Matrix newMatrix = new Matrix(getSize()[1], getSize()[0]);
        for (int i = 0; i < getSize()[1]; i++) {
            newMatrix.setVectorByIndex(i, getVectorColumnByIndex(i));
        }
        this.matrixElements = newMatrix.matrixElements;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);
        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);
        return newMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize()[1] != matrix2.getSize()[0]) {
            throw new IllegalArgumentException("Умножение невозможно: число столбцов первой матрицы не соответствует числу строк второй матрицы");
        }
        double[][] array = new double[matrix2.getSize()[0]][matrix2.getSize()[1]];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Vector.getScalarMultiplication(matrix1.getVectorByIndex(i), matrix2.getVectorColumnByIndex(j));
            }
        }
        return new Matrix(array);
    }


}

