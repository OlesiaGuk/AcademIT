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
        Vector vector2 = new Vector(vector);
        System.out.println("Копия вектора vector: " + vector2);
        vector2.setComponentByIndex(10, 0);
        System.out.println("Установка компоненты 10 по индексу 0 в вектор vector2 = " + vector2);

        System.out.println();
        System.out.println("Сумма векторов vector и vector2 = " + Vector.getSum(vector, vector2));
        System.out.println("Разность векторов vector и vector2 = " + Vector.getDifference(vector, vector2));

        System.out.println();
        Vector vector3 = new Vector(5, array);
        System.out.println("vector = " + vector);
        System.out.println("vector3 = " + vector3);
        System.out.println("vector == vector? " + vector3.equals(vector));

        System.out.println();
        Vector testVector1 = new Vector(new double[]{-1, 2, -5});
        Vector testVector2 = new Vector(new double[]{3, -3, -3});
        System.out.println("testVector1 = " + testVector1);
        System.out.println("testVector2 = " + testVector2);
        System.out.println("Скалярное произведение testVector1 testVector2 = " + Vector.getScalarMultiplication(testVector1, testVector2));

        testVector1.reverse();
        System.out.println("Разворот вектора testVector1: " + testVector1);
        testVector1.multiplyByScalar(2);
        System.out.println("Умножение вектора testVector1 на 2: " + testVector1);
    }
}
