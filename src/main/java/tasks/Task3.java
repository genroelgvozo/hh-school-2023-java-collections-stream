package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  // Тут просто надо было найти .thenComparing, на удивление все сразу сделали, в прошлом году были попытки 3 раза сортировать,
  // но в обратном порядке, а так как сортировка стабильная, то срабатывало (но костыльно)
  public static List<Person> sort(Collection<Person> persons) {
    return persons.stream()
        // nullsFirst/nullsLast видел у одного человека только, за это ставил 4
        // Ни у кого больше не требовал, так как в тестах нет проверки этого (надо бы поправить)
        // Фамилия и Имя могут быть null (в отличие от createdAt по смыслу)
        .sorted(Comparator.comparing(Person::getSecondName, Comparator.nullsLast(String::compareTo))
            .thenComparing(Person::getFirstName, Comparator.nullsLast(String::compareTo))
            .thenComparing(Person::getCreatedAt)
        )
        .toList();
  }
}
