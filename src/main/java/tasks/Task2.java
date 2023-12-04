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


    return Stream
        .concat(
            persons1.stream(),
            persons2.stream())
        .sorted(Comparator.comparing(Person::getCreatedAt))
        .limit(limit)
        .collect(Collectors.toList());

    // сложность по времени - O(logN) ( logN - наибольшая сложность - процедура сортировки)
    // сложность по памяти - O(n) (Величина входных данных)


    /*
      .sorted(new Comparator<Person>() { @Override
        public int compare(Person o1, Person o2) {
        return (o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        }})
     */



  }
}
