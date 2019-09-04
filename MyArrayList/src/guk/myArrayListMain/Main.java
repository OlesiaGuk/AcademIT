package guk.myArrayListMain;

import guk.myArrayList.MyArrayList;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>(10);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(2, 3);
        System.out.println("Список arrayList: " + arrayList);

        System.out.println("Размер arrayList = " + arrayList.size());
        System.out.println(arrayList.isEmpty());

        int index1 = 1;
        System.out.println("Элемент по индексу " + index1 + " = " + arrayList.get(index1));

        int index2 = 1;
        System.out.println("Заменен элемент по индексу " + index2 + " = " + arrayList.set(index2, 5));
        System.out.println("Список arrayList: " + arrayList);

        System.out.println(arrayList.contains(50));

        arrayList.remove(Integer.valueOf(1));
        System.out.println("Список arrayList: " + arrayList);

        Integer[] a = new Integer[]{50, 30};
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(a));

        System.out.println(arrayList.removeAll(linkedList));
        System.out.println("Список arrayList: " + arrayList);
    }
}
