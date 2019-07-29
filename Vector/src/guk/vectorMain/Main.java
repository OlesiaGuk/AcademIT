package guk.vectorMain;

import guk.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector emptyVector = new Vector(3);
        System.out.println("Пустой вектор: " + emptyVector);

        double[] array = {-3, 4};
        Vector vector = new Vector(2, array);
        System.out.println("Вектор vector = " + vector);
        System.out.println("Длина вектора vector = " + vector.getLength());

        Vector vectorCopy = new Vector(vector);
        System.out.println("Копия вектора vector: " + vectorCopy);

        int index = 1;
        System.out.printf("Компонента вектора vector по индексу %d = %.2f %n", index, vector.getComponentByIndex(index));

        System.out.println();
        Vector vector1 = new Vector(new double[]{5, 10, 20});
        System.out.println("Вектор vector1 = " + vector1);

        vector.add(vector1);
        System.out.println("Сумма vector и vector1 = " + vector);

        vector.subtract(vector1);
        System.out.println("Разность vector и vector1 = " + vector);

        System.out.println();

        Vector vector2 = vector.setComponentByIndex(10, 0);
        System.out.println("vector2 = " + vector2);

        System.out.println("Скалярное произведение vector и vector2 = " + Vector.getScalarMultiplication(vector, vector2));
        System.out.println("Разность векторов vector и vector2 = " + Vector.getDifference(vector, vector2));
        //System.out.println("Разность векторов vector и vector2 = " + vector.getDifference(vector2));
        System.out.println("Сумма векторов vector и vector2 = " + Vector.getSum(vector, vector2));

        Vector vector3 = new Vector(5, array);
        System.out.println(vector.equals(vector3));
    }
}
