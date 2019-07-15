package guk.rangeDif;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-5, 5);
        Range secondRange = new Range(-10, 10);

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
