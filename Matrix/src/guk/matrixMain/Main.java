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

        Matrix matrix2Copy = new Matrix(matrix2);
        System.out.println("Копия матрицы matrix2 = " + matrix2Copy);

        System.out.println();
        Vector[] vector = new Vector[]{new Vector(new double[]{7, 8}), new Vector(new double[]{9, 10, 11})};
        Matrix matrix3 = new Matrix(vector);
        System.out.println("Матрица matrix3 = " + matrix3);

        int index = 1;
        System.out.println("Вектор под индексом " + index + " матрицы matrix3 = " + matrix3.getVectorByIndex(index));

        int index3 = 2;
        System.out.println(matrix3.getVectorColumnByIndex(index3));

        int index2 = 0;
        matrix2Copy.setVectorByIndex(index2, new Vector(2, new double[]{20, 21}));
        System.out.println("Установка вектора-строки {20, 21} по индексу " + index2 + " в матрицу matrix2Copy = " + matrix2Copy);

    }
}
