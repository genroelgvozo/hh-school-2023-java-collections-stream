package tasks;

import common.Person;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    Map<Instant, Person> persons;
    List<Person> personsLimitCounts;

    persons = Stream.concat(
                    persons1.stream(),
                    persons2.stream())
            .collect(Collectors.toMap(
                    x -> x.getCreatedAt(),
                    x -> x,
                    (x1, x2) -> x1,
                    TreeMap::new));

    personsLimitCounts = persons.entrySet().stream()
            .limit(limit)
            .map(x -> x.getValue())
            .collect(Collectors.toList());

    return  personsLimitCounts;

    // сложность по времени - O(logN) ( logN - добавление элементов в сортированную таблицу по величине CreatedAt)
    // сложность по памяти - O(n) ( поля КЛЮЧ и ЗНАЧЕНИЕ для Хэш-таблицы и поле ЗНАЧЕНИЕ в списке )
  }
}
