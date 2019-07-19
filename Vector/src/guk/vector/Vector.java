package guk.vector;

public class Vector {
    private int n;
    private double[] componentsArray;

    public Vector(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Размерность вектора должны быть > 0");
        this.n = n;
        componentsArray = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.n, vector.componentsArray);
    }

    public Vector(double[] array) {
        n = array.length;
        componentsArray = new double[n];
        for (int i = 0; i < n; i++) {
            componentsArray[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        this.n = n;
        componentsArray = new double[n];
        for (int i = 0; i < array.length; i++) {
            componentsArray[i] = array[i];
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[] getComponentsArray() {
        return componentsArray;
    }

    public void setComponentsArray(double[] componentsArray) {
        this.componentsArray = componentsArray;
    }

    //Метод getSize() для получения размерности вектора - чем отличается от геттера для поля n???
    public int getSize(){
        return n;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 0;
        s.append("{");
        while (i < n - 1) {
            s.append(componentsArray[i]).append(", ");
            i++;
        }
        s.append(componentsArray[i]).append("}");
        String line = s.toString();
        return line;
    }
}
