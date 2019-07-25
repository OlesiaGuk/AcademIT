package guk.matrixMain;

import guk.matrix.Matrix;
import guk.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2, 3);
        System.out.println(matrix);

        double[][] array = {{1, 2, 3}, {4, 5, 6, 7}};
        Matrix matrix2 = new Matrix(array);
        System.out.println(matrix2);

        double[] array2 = {7, 8};
        double[] array3 = {9, 10, 11};
        Vector[] vector = {new Vector(array2), new Vector(array3)};
        Matrix matrix3 = new Matrix(vector);
        System.out.println(matrix3);

        Matrix matrixCopy = new Matrix(matrix3);
        System.out.println(matrixCopy);

        System.out.println(Arrays.toString(matrix2.getMatrixSize()));

        System.out.println(matrix3.getVectorByIndex(1));

        Vector newVector = new Vector(2, new double[]{20, 21});
        System.out.println(matrixCopy.setVectorByIndex(0, newVector));
    }
}
