package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Задача 5
Расширим предыдущую задачу
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }


  // Чуть больше чем в 4 таске - просто лямбда, и написать get из мапы
  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    return persons.stream()
        .map(person -> personConverter.convert(person, personAreaIds.get(person.getId())))
        .toList();
  }
}
