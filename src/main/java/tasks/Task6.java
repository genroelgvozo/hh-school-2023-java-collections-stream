package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    HashSet<String> StringsRegionsForEachPerson = new HashSet<>();

    Map<Integer, String> tableOfRegionsName = new HashMap<>();

    areas.stream()
            .forEach(x -> tableOfRegionsName.put(x.getId(), x.getName()));

    persons.stream()
            .flatMap(x -> (personAreaIds.get(x.getId()).stream())
                    .map(y -> x.getFirstName() + " - " + tableOfRegionsName.get(y)))
            .forEach(x -> StringsRegionsForEachPerson.add(x));



    return StringsRegionsForEachPerson;
  }
}
