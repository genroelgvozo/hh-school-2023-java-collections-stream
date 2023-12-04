package tasks;

import common.Person;
import common.PersonService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;

/*
асимптотика:
  -создание словаря O(n)
  -заполнение массива O(n)
Итог: O(n)
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {

    Set<Person> persons = personService.findPersons(personIds);

    List<Person> sorted_persons = new ArrayList<>(personIds.size());

    Map<Integer, Person> id_person = new HashMap<>();

    for (Person person : persons){

        id_person.put(person.getId(), person);

    }
    for (Integer id : personIds){

        sorted_persons.add(id_person.get(id));

    }

    return sorted_persons;
  }
}

