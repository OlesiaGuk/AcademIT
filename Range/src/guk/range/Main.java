package guk.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(0, 100);

        System.out.printf("Длина от %.2f до %.2f = %.2f %n", range.getFrom(), range.getTo(), range.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число для проверки принадлежности к интервалу: ");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.printf("Число %.2f принадлежит диапазону от %.2f до %.2f", number, range.getFrom(), range.getTo());
        } else {
            System.out.printf("Число %.2f не принадлежит диапазону от %.2f до %.2f", number, range.getFrom(), range.getTo());
        }
    }
}
