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
    TreeMap<Instant, Person> persons = new TreeMap<>();
    ArrayList<Person> personsLimitCounts = new ArrayList<>();


    Stream
           .concat(
           persons1.stream(),
           persons2.stream())
           .forEach(x ->
                   persons.put(x.getCreatedAt(),x)
           );

    persons.entrySet().stream()
            .limit(limit)
            .forEach(y -> {
              personsLimitCounts.add(y.getValue());
            });

    return  personsLimitCounts;

    // сложность по времени - O(logN) ( logN - добавление элементов в сортированную таблицу по величине CreatedAt)
    // сложность по памяти - O(n) ( поля КЛЮЧ и ЗНАЧЕНИЕ для Хэш-таблицы и поле ЗНАЧЕНИЕ в списке )
  }
}
