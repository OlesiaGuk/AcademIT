package guk.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Иван", 21),
                new Person("Иван", 17),
                new Person("Сергей", 22),
                new Person("Мария", 16),
                new Person("Алексей", 30)));

        // а) получить список уникальных имен
        List<String> distinctNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        // б) вывести список уникальных имен в заданном формате
        String distinctNamesString = distinctNames.stream()
                .collect(Collectors.joining(", ", "Уникальные имена: ", "."));

        System.out.println(distinctNamesString);
        System.out.println();

        // в) получить список людей младше 18, посчитать для них средний возраст
        List<Person> under18Persons = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        double averageAge = under18Persons.stream()
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println("Список людей, младше 18: " + under18Persons);
        System.out.println("Средний возраст людей младше 18 лет = " + averageAge);
        System.out.println();

        // д) получить список людей в возрасте от 20 до 45 лет,
        //    вывести в консоль их имена в порядке убывания возраста
        List<Person> personsFrom20To45 = persons.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .collect(Collectors.toList());

        System.out.println("Люди в возрасте от 20 до 45 лет:");
        System.out.println(personsFrom20To45);

        System.out.println("Их имена в порядке убывания возраста: " + personsFrom20To45.stream()
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "))
        );
        System.out.println();

        // г) при помощи группировки получить Map, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> newMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingDouble(Person::getAge)));

        newMap.forEach((name, avAge) -> System.out.println("Имя: " + name + ", средний возраст = " + avAge));
    }
}
