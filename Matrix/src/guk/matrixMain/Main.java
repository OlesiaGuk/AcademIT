package guk.matrixMain;

import guk.matrix.Matrix;
import guk.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2, 3);
        System.out.println("Матрица нулей matrix размера " + Arrays.toString(matrix.getSize()) + " = " + matrix);

        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6, 7}};
        Matrix matrix2 = new Matrix(array);
        System.out.println("Матрица matrix2 = " + matrix2);
        System.out.println("Размер матрицы matrix2 = " + Arrays.toString(matrix2.getSize()));

        Matrix matrix2Copy = new Matrix(matrix2);
        System.out.println("Копия матрицы matrix2 = " + matrix2Copy);

        System.out.println();
        int index2 = 0;
        matrix2Copy.setVectorByIndex(index2, new Vector(4, new double[]{20, 21}));
        System.out.println("Установка вектора-строки {20, 21} по индексу " + index2 + " в матрицу matrix2Copy = " + matrix2Copy);

        System.out.println();
        Vector[] vector = new Vector[]{new Vector(new double[]{7, 8}), new Vector(new double[]{9, 10, 11})};
        Matrix matrix3 = new Matrix(vector);
        System.out.println("Матрица matrix3 = " + matrix3);

        int index = 1;
        System.out.println("Вектор под индексом " + index + " матрицы matrix3 = " + matrix3.getVectorByIndex(index));

        int index3 = 2;
        System.out.println("Вектор-столбец из матрицы matrix3 по индексу " + index3 + " = " + matrix3.getVectorColumnByIndex(index3));

        matrix3.multiplyByScalar(2);
        System.out.println("Умножение матрицы matrix3 на скаляр 2 = " + matrix3);

        System.out.println();
        Matrix matrix4 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{4, 5, 6})});
        System.out.println("matrix4 = " + matrix4);

        matrix3.add(matrix4);
        System.out.println("Сумма матриц matrix3 и matrix4 = " + matrix3);

        matrix3.subtract(matrix4);
        System.out.println("Разность матриц matrix3 и matrix4 = " + matrix3);

        System.out.println();
        System.out.println("matrix3 = " + matrix3);
        System.out.println("matrix3 = " + matrix4);
        System.out.println("Сумма матриц matrix3 и matrix4 (стат.метод) = " + Matrix.getSum(matrix3, matrix4));
        System.out.println("Разность матриц matrix3 и matrix4 (стат.метод) = " + Matrix.getDifference(matrix3, matrix4));

        System.out.println();
        Vector vector1 = new Vector(new double[]{5, 8, -4});
        Vector vector2 = new Vector(new double[]{6, 9, -5});
        Vector vector3 = new Vector(new double[]{4, 7, -3});
        Matrix testMatrix1 = new Matrix(new Vector[]{vector1, vector2, vector3});

        Vector vector4 = new Vector(new double[]{3, 2, 5});
        Vector vector5 = new Vector(new double[]{4, -1, 3});
        Vector vector6 = new Vector(new double[]{9, 6, 5});
        Matrix testMatrix2 = new Matrix(new Vector[]{vector4, vector5, vector6});

        System.out.println("testMatrix1 = " + testMatrix1);
        System.out.println("testMatrix2 = " + testMatrix2);
        System.out.println("Умножение матриц: " + Matrix.getMultiplication(testMatrix1, testMatrix2));

        System.out.println();
        double[][] array2 = new double[][]{{1, 2, 3}, {4, 5, 6}};
        Matrix testMatrix3 = new Matrix(array2);
        System.out.println("testMatrix3 = " + testMatrix3);
        testMatrix3.transpose();
        System.out.println("Транспонированная матрица testMatrix3 = " + testMatrix3);

        System.out.println();
        Vector vector7 = new Vector(new double[]{2, 4, 0});
        Vector vector8 = new Vector(new double[]{-2, 1, 3});
        Vector vector9 = new Vector(new double[]{-1, 0, 1});
        Matrix testMatrix4 = new Matrix(new Vector[]{vector7, vector8, vector9});
        System.out.println("Матрица testMatrix4 = " + testMatrix4);

        Vector vector10 = new Vector(new double[]{1, 2, -1});
        System.out.println("Вектор vector10 = " + vector10);

        System.out.println("Умножение матрицы testMatrix4 на вектор vector10: " + testMatrix4.multiplyByVectorColumn(vector10));

        System.out.println();
        double[][] array3 = {{4, 1, 1, 2, 1}, {1, 2, -1, 1, 1}, {3, 1, 1, 1, 1}, {2, 1, 1, 4, 1}, {2, -1, 1, 1, 5}};
        //double[][] array3 = {{101.8}};
        Matrix matrix5 = new Matrix(array3);
        System.out.println("matrix5 = " + matrix5);
        System.out.println("Определитель матрицы matrix5 = " + matrix5.getDeterminant());

    }
}
