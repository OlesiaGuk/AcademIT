package guk.rangeMain;

import guk.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-5, 5);

        System.out.printf("Длина от %.2f до %.2f = %.2f %n", range.getFrom(), range.getTo(), range.getLength());

        double number = 0;
        if (range.isInside(number)) {
            System.out.printf("Число %.2f принадлежит диапазону от %.2f до %.2f %n", number, range.getFrom(), range.getTo());
        } else {
            System.out.printf("Число %.2f не принадлежит диапазону от %.2f до %.2f %n", number, range.getFrom(), range.getTo());
        }

        Range secondRange = new Range(-2, 7);

        System.out.println();
        System.out.println("Пересечение: " + range.getIntersection(secondRange));

        System.out.print("Объединение: ");
        Range[] unionRange = range.getUnion(secondRange);
        for (Range e : unionRange) {
            System.out.print(e);
        }
        System.out.println();

        System.out.print("Разность: ");
        Range[] differenceRange = range.getDifference(secondRange);
        if (differenceRange.length == 0) {
            System.out.println('\u00D8');
        } else {
            for (Range e : differenceRange) {
                System.out.print(e);
            }
        }
    }
}
