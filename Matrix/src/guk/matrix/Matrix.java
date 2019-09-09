package guk.matrix;

import guk.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть > 0");
        }

        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }

        int secondDimensionLength = 0;
        for (double[] a : array) {
            secondDimensionLength += a.length;
        }

        if (secondDimensionLength == 0) {
            throw new IllegalArgumentException("Невозможно создать матрицу размера 0");
        }

        rows = new Vector[array.length];
        int maxSize = findMaxNestedArraySize(array);
        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0");
        }

        rows = new Vector[vectorsArray.length];
        int maxSize = findMaxVectorSize(vectorsArray);
        for (int i = 0; i < vectorsArray.length; i++) {
            rows[i] = new Vector(maxSize);
            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                rows[i].setComponentByIndex(vectorsArray[i].getComponentByIndex(j), j);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
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
        for (int i = 0; i < rows.length; i++) {
            s.append(rows[i].toString());

            if (i != rows.length - 1) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + rows.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }

        return new Vector(rows[index]);
    }

    public void setRowByIndex(int index, Vector newVector) {
        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть < " + rows.length);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (newVector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Размерность переданного вектора не соответствует размерности векторов в матрице");
        }

        rows[index] = new Vector(newVector);
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Введенный индекс превышает размерность вектора");
        }

        double[] array = new double[rows.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rows[i].getComponentByIndex(index);
        }
        return new Vector(array);
    }

    public void multiplyByScalar(double scalar) {
        for (Vector e : rows) {
            e.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }

        for (int i = 0; i < getRowsAmount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Матрицы не совпадают по размеру");
        }

        for (int i = 0; i < getRowsAmount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public void transpose() {
        if (getRowsAmount() == getColumnsAmount()) {
            for (int i = 0; i < getRowsAmount(); i++) {
                for (int j = i + 1; j < getColumnsAmount(); j++) {
                    double temp = rows[i].getComponentByIndex(j);
                    rows[i].setComponentByIndex(rows[j].getComponentByIndex(i), j);
                    rows[j].setComponentByIndex(temp, i);
                }
            }
        } else {
            Vector[] newArray = new Vector[getColumnsAmount()];

            for (int j = 0; j < getColumnsAmount(); j++) {
                newArray[j] = getColumnByIndex(j);
            }
            rows = newArray;
        }
    }

    public Vector multiplyByVectorColumn(Vector vector) {
        if (getColumnsAmount() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов матрицы не соответствует размерности вектора-столбца");
        }

        double[] array = new double[getRowsAmount()];
        for (int i = 0; i < array.length; i++) {
            array[i] = Vector.getScalarMultiplication(rows[i], vector);
        }
        return new Vector(array);
    }

    public double getDeterminant() {
        if (getRowsAmount() != getColumnsAmount()) {
            throw new IllegalArgumentException("Число строк в матрице не равно числу столбцов!"); //Определитель можно вычислить только для квадратной матрицы
        }

        int matrixSize = getRowsAmount();
        if (matrixSize == 1) {
            return rows[0].getComponentByIndex(0);
        }
        if (matrixSize == 2) {
            return rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1) - rows[1].getComponentByIndex(0) * rows[0].getComponentByIndex(1);
        }

        double determinant = 0;
        int decompositionLineNumber = 0;
        for (int j = 0; j < matrixSize; j++) {
            double element = rows[decompositionLineNumber].getComponentByIndex(j);
            double[][] extraMinor = new double[matrixSize - 1][matrixSize - 1];

            for (int x = 0, k = 0; k < extraMinor.length; x++, k++) {
                for (int y = 0, m = 0; m < extraMinor.length; y++, m++) {
                    if (x == decompositionLineNumber) {
                        x++;
                    }
                    if (y == j) {
                        y++;
                    }
                    extraMinor[k][m] = rows[x].getComponentByIndex(y);
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
                array[i][j] = Vector.getScalarMultiplication(matrix1.rows[i], matrix2.getColumnByIndex(j));
            }
        }
        return new Matrix(array);
    }
}

