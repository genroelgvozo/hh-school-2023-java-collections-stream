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

        Map <Integer, Person> IdToPersons = personService.findPersons(personIds).stream()
            .collect(Collectors.toMap(idPerson -> idPerson.getId(), x -> x));

        return  personIds.stream()
            .map(idPerson -> IdToPersons.get(idPerson))
            .collect(Collectors.toCollection(ArrayList::new));

        //т.к. создается Мапа и Лист величины n (операция добавления весит 0(1) соотв.) то
        // сложность по времени - O(n)
        // сложность по памяти - O(n)

    }
}