package guk.matrix;

import guk.vector.Vector;

public class Matrix {
    private Vector[] elementsTable;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }
        elementsTable = new Vector[n];
        for (int i = 0; i < n; i++) {
            elementsTable[i] = new Vector(m);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }
        elementsTable = new Vector[array.length];
        int maxSize = findMaxNestedArraySize(array);
        for (int i = 0; i < array.length; i++) {
            elementsTable[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }
        elementsTable = new Vector[vectorsArray.length];
        int maxSize = findMaxVectorSize(vectorsArray);
        for (int i = 0; i < vectorsArray.length; i++) {
            elementsTable[i] = new Vector(maxSize);
            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                elementsTable[i].setComponentByIndex(vectorsArray[i].getComponentByIndex(j), j);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.elementsTable);
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
        for (int i = 0; i < elementsTable.length; i++) {
            s.append(elementsTable[i].toString());

            if (i != elementsTable.length - 1) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }

    public int getRowsAmount() {
        return elementsTable.length;
    }

    public int getColumnsAmount() {
        return elementsTable[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index >= elementsTable.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + elementsTable.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }

        return new Vector(elementsTable[index]);
    }

    public void setRowByIndex(int index, Vector newVector) {
        if (index >= elementsTable.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + elementsTable.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (newVector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Размерность переданного вектора не соответствует размерности векторов в матрице");
        }

        elementsTable[index] = new Vector(newVector);
    }

    public Vector getVectorColumnByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }

        double[] array = new double[elementsTable.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = elementsTable[i].getComponentByIndex(index);
        }
        return new Vector(array);
    }

    public void multiplyByScalar(double scalar) {
        for (Vector e : elementsTable) {
            e.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }
        for (int i = 0; i < getRowsAmount(); i++) {
            elementsTable[i].add(matrix.elementsTable[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }
        for (int i = 0; i < getRowsAmount(); i++) {
            elementsTable[i].subtract(matrix.elementsTable[i]);
        }
    }

    public void transpose() {
        if (getRowsAmount() == getColumnsAmount()) {
            for (int i = 0; i < getRowsAmount(); i++) {
                for (int j = i + 1; j < getColumnsAmount(); j++) {
                    double temp = elementsTable[i].getComponentByIndex(j);
                    elementsTable[i].setComponentByIndex(elementsTable[j].getComponentByIndex(i), j);
                    elementsTable[j].setComponentByIndex(temp, i);
                }
            }
        } else {
            Vector[] newArray = new Vector[getColumnsAmount()];

            for (int j = 0; j < getColumnsAmount(); j++) {
                newArray[j] = getVectorColumnByIndex(j);
            }
            elementsTable = newArray;
        }
    }

    public Vector multiplyByVectorColumn(Vector vector) {
        if (getColumnsAmount() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов матрицы не соответствует размерности вектора-столбца");
        }
        double[] array = new double[getRowsAmount()];
        for (int i = 0; i < array.length; i++) {
            array[i] = Vector.getScalarMultiplication(getRowByIndex(i), vector);
        }
        return new Vector(array);
    }

    public double getDeterminant() {
        if (getRowsAmount() != getColumnsAmount()) {
            throw new IllegalArgumentException("Число строк в матрице не равно числу столбцов!"); //Определитель можно вычислить только для квадратной матрицы
        }

        int matrixSize = getRowsAmount();
        if (matrixSize == 1) {
            return elementsTable[0].getComponentByIndex(0);
        }
        if (matrixSize == 2) {
            return elementsTable[0].getComponentByIndex(0) * elementsTable[1].getComponentByIndex(1) - elementsTable[1].getComponentByIndex(0) * elementsTable[0].getComponentByIndex(1);
        }

        double determinant = 0;
        int decompositionLineNumber = 0;
        for (int j = 0; j < matrixSize; j++) {
            double element = elementsTable[decompositionLineNumber].getComponentByIndex(j);
            double[][] extraMinor = new double[matrixSize - 1][matrixSize - 1];

            for (int x = 0, k = 0; k < extraMinor.length; x++, k++) {
                for (int y = 0, m = 0; m < extraMinor.length; y++, m++) {
                    if (x == decompositionLineNumber) {
                        x++;
                    }
                    if (y == j) {
                        y++;
                    }
                    extraMinor[k][m] = elementsTable[x].getComponentByIndex(y);
                }
            }
            Matrix extraMinorMatrix = new Matrix(extraMinor);
            determinant += Math.pow(-1, j) * element * extraMinorMatrix.getDeterminant();
        }
        return determinant;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);
        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);
        return newMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не соответствует числу строк второй матрицы");
        }

        double[][] array = new double[matrix1.getRowsAmount()][matrix2.getColumnsAmount()];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Vector.getScalarMultiplication(matrix1.getRowByIndex(i), matrix2.getVectorColumnByIndex(j));
            }
        }
        return new Matrix(array);
    }
}

