package tasks;

import common.Person;
import common.PersonService;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;
/*
Создание мар за O(n)
Вывод за O(n)
Итог: O(n)
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer, Person> map = persons.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
    return personIds.stream().map(p -> map.get(p)).collect(Collectors.toList());
  }
}
