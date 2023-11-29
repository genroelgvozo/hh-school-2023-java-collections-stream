package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 {

    private final PersonService personService;

    public Task1(PersonService personService) {
        this.personService = personService;
    }

    public List<Person> findOrderedPersons(List<Integer> personIds) {

        Map <Integer, Person> tableOfPersons;
        List<Person> OrderedListOfPersons;

        tableOfPersons = personService.findPersons(personIds).stream()
                .collect(Collectors.toMap(
                        x -> x.getId(),
                        x -> x)
                );

        OrderedListOfPersons = personIds.stream()
                .map(x -> tableOfPersons.get(x))
                .collect(Collectors.toCollection(LinkedList::new));

        return OrderedListOfPersons;

        // сложность по времени - O(1) ( Хэш-таблица - добавление за О(1) + Лист связный список - добавление за О(1))
        // сложность по памяти - O(n) ( поля КЛЮЧ и ЗНАЧЕНИЕ для Хэш-таблицы и поле ЗНАЧЕНИЕ в связном списке )

    }
}