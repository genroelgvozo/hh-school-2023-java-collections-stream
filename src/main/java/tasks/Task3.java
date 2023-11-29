package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    List<Person> sortedList;

      sortedList =   persons.stream()
                        .sorted(Comparator
                                .comparing(Person::getFirstName)
                                .thenComparing(Person::getSecondName)
                                .thenComparing(Person::getCreatedAt))
                        .collect(Collectors.toList());

    return sortedList;

      // сложность по времени - O(n * logN * logN) ( logN - добавление элементов в сортированную таблицу по величине
      // CreatedAt и getSecondName и для каждого getFirstName которых n)
      // сложность по памяти - O(n) ( поле ЗНАЧЕНИЕ в списке )




    // не потерять - мануал откуда брал пример
    // https://user12vv.wordpress.com/2017/06/05/%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0-%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BE%D0%B2-%D0%B2-java8-comparator-comparing-reversed/
  }
}
