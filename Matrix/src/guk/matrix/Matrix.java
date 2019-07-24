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
            matrixElementsArray[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        matrixElementsArray = vectorsArray;
    }

    public Matrix(Matrix matrix){
        this(matrix.matrixElementsArray);
    }

    public Vector[] getMatrixElementsArray() {
        return matrixElementsArray;
    }

    public void setMatrixElementsArray(Vector[] matrixElementsArray) {
        this.matrixElementsArray = matrixElementsArray;
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
}
