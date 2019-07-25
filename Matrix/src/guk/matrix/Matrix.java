package guk.matrix;

import guk.vector.Vector;

public class Matrix {
    private Vector[] matrixElementsArray;

    public Matrix(int n, int m) {
        matrixElementsArray = new Vector[n];
        for (int i = 0; i < n; i++) {
            matrixElementsArray[i] = new Vector(m);
        }
    }

    public Matrix(double[][] array) {
        matrixElementsArray = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            matrixElementsArray[i] = new Vector(findMaxSize(array), array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) { //todo: добить нулями
        matrixElementsArray = vectorsArray;
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrixElementsArray);
    }

    public Vector[] getMatrixElementsArray() {
        return matrixElementsArray;
    }

    public void setMatrixElementsArray(Vector[] matrixElementsArray) {
        this.matrixElementsArray = matrixElementsArray;
    }

    private int findMaxVectorSize() {//todo: метод неоптимальный, переделать?
        int maxVectorIndex = 0;
        for (int i = 1; i < matrixElementsArray.length; i++) {
            maxVectorIndex = (matrixElementsArray[i].getSize() > matrixElementsArray[maxVectorIndex].getSize()) ? i : maxVectorIndex;
        }
        return matrixElementsArray[maxVectorIndex].getSize();
    }

    private static int findMaxSize(double[][] array) {//todo: метод неоптимальный, переделать?
        int maxSizeElementIndex = 0;
        for (int i = 1; i < array.length; i++) {
            maxSizeElementIndex = (array[i].length > array[maxSizeElementIndex].length) ? i : maxSizeElementIndex;
        }
        return array[maxSizeElementIndex].length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        int i = 0;
        while (i < matrixElementsArray.length) {
            s.append("{");
            int j = 0;
            while (j < matrixElementsArray[i].getSize() - 1) {
                s.append(matrixElementsArray[i].getVectorComponentByIndex(j)).append(", ");
                j++;
            }
            s.append(matrixElementsArray[i].getVectorComponentByIndex(j)).append("}");
            if (i != matrixElementsArray.length - 1) {
                s.append(", ");
            }
            i++;
        }
        s.append("}");
        return s.toString();
    }

    public int[] getMatrixSize() {
        return new int[]{matrixElementsArray.length, findMaxVectorSize()};
    }

    public Vector getVectorByIndex(int index) {
        if (index >= matrixElementsArray.length) {
            throw new IllegalArgumentException("Индекс должен быть < " + matrixElementsArray.length);
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        return matrixElementsArray[index];
    }

    public Matrix setVectorByIndex(int index, Vector newVector) {
        if (index >= matrixElementsArray.length + 1) {
            throw new IllegalArgumentException("Индекс должен быть < " + matrixElementsArray.length);
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть >= 0");
        }
        Vector[] newMatrixElementsArray = new Vector[matrixElementsArray.length + 1];
        int i = 0;
        while (i < index) {
            newMatrixElementsArray[i] = matrixElementsArray[i];
            i++;
        }
        int j = i;
        newMatrixElementsArray[j] = newVector;
        j++;
        while (j < newMatrixElementsArray.length) {
            newMatrixElementsArray[j] = matrixElementsArray[i];
            i++;
            j++;
        }
        return new Matrix(newMatrixElementsArray);
    }
}
