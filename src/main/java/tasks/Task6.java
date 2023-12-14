package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  /*
  1. Идея с мапой чтобы не искать арейки перебором и не сделать O(nm)
  2. filter практически ни у кого не было, но теста на такое нет - не требовал (поправлю!)
  3. писал много кому про формирование строки - здесь оно вынесено в метод. Это хорошая практика - это некий формат, просто его найти,
  просто его поправить при смене. И тогда понимаешь что удобно делать мапу на полную арейку, а не сразу на имя как некоторые делали
  Сейчас требуется только имя, но кто знает как нужно будет потом...

  Задачка тут упрощенная, но в целом встречается - когда два типа данных достаются из разных источников/таблиц, и не могут быть получены "сразу вместе"
  Иногда приходится так вот изощряться
   */
  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Area> areaMap = areas.stream()
        .collect(Collectors.toMap(Area::getId, Function.identity()));
    return persons.stream()
        .filter(person -> personAreaIds.containsKey(person.getId()))
        .flatMap(person -> personAreaIds.get(person.getId()).stream()
            .map(areaId -> getPersonAreaDescription(person, areaMap.get(areaId)))
        )
        .collect(Collectors.toSet());
  }

  private static String getPersonAreaDescription(Person person, Area area) {
    return "%s - %s".formatted(person.getFirstName(), area.getName());
  }
}
