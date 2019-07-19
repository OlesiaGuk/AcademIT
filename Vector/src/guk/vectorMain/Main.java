package guk.vectorMain;

import guk.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[] array = {1,2,3};
        Vector vector = new Vector( array);
        System.out.println(vector);

        System.out.println(vector.getSize());
    }
}
