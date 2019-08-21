package guk.listMain;

import guk.List.ListItem;
import guk.List.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        ListItem<Integer> item1 = new ListItem<>(2);
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(item1);

        list.addToStart(1);
        list.addByIndex(0, 0);
        list.addByIndex(3, 3);
        System.out.println("Список list: " + list);

        int index1 = 3;
        System.out.println("Значение по индексу " + index1 + " = " + list.getDataByIndex(index1));
        System.out.println("Размер списка = " + list.getSize());

        System.out.println();
        int index2 = 3;
        System.out.println("По индексу " + index2 + " удален элемент со значением " + list.deleteItemByIndex(index2));
        System.out.println("Список list: " + list);

        System.out.println();
        list.reverse();
        System.out.println("Развернутый список list: " + list);

        System.out.println();
        System.out.println("Значение первого элемента: " + list.getHeadData());

        int index3 = 0;
        System.out.println("По индексу " + index3 + " заменен элемент со значением " + list.setDataByIndex(10, 0));
        System.out.println("Список list: " + list);

        System.out.println();
        int dataToDelete = 10;
        System.out.println("Удаление узла со значением = " + dataToDelete + ": " + list.deleteItemByData(dataToDelete));
        System.out.println("Список list: " + list);

        System.out.println("Из списка list удален первый элемент со значением " + list.deleteFirstItem());
        System.out.println("Список list: " + list);

        list.addByIndex(10, 1);
        System.out.println("В list добавлен элемент по индексу 1: " + list);

        System.out.println();
        SinglyLinkedList<Integer> list2 = list.copy();
        System.out.println("Копия списка list : " + list2);
    }
}
