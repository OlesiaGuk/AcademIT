package guk.vectorMain;

import guk.vector.Vector;

public class Main {
    public static void main(String[] args) {

        Vector emptyVector = new Vector(3);
        System.out.println(emptyVector);

        double[] array = {-3, 4};
        Vector vector = new Vector(5, array);
        System.out.println("vector = " + vector);

        Vector vectorCopy = new Vector(vector);
        System.out.println("Копия вектора vector: " + vectorCopy);

        int index = 1;
        System.out.printf("Компонента вектора по индексу %d = %.2f %n", index, vector.getVectorComponentByIndex(index));

        Vector vector2 = vector.setVectorComponentByIndex(10, 0);
        System.out.println("vector2 = " + vector2);

        System.out.println("Разность векторов vector и vector2 = " + vector.getVectorsDifference(vector2));

        Vector vector3 = new Vector(5, array);
        System.out.println(vector.equals(vector3));


    }
}
