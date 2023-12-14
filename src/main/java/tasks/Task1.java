package tasks;

import common.Person;
import common.PersonService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
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

  /*
  Тут была сложность именно алгоритмически - как сделать за O(n) а не квадрат или дольше (да, такое тоже было)
  Концептуально такое часто встречается - вроде бы надо изменить одно, а пляшем от другого - берем айдишки, а по ним находим быстро персон
  А быстро это сделать естественно в хеш-таблице

  Что подчерпнуть:
  1. Предпочитайте method reference лямбде если можно - idPersonMap::get вместо id -> idPersonMap.get(id)
  2. Вместо x -> x можно писать Function.identity(), но тут на вкус в целом

  Где такое встречалось:
  1. Ищем кандидатов по сложным фильтрам в поисков системе (например Elasticsearch), но там удобно брать только id сущностей,
  так как сами сущности могут быть неактуальны, актуальность всегда в базе
  2. По id идем в базу за актуальными кандидатами, тут же проверяя вообще доступность для клиента
  3. Но база выдает в рандомном порядке, а поисковая система выдала айдишки в нужном - согласно скору при поиске - сортируем как в задаче
   */
  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Map<Integer, Person> idPersonMap = personService.findPersons(personIds).stream()
        .collect(Collectors.toMap(Person::getId, Function.identity()));
    return personIds.stream()
        .map(idPersonMap::get)
        .filter(Objects::nonNull)
        // Вот этого практически ни у кого не было - personService может кого-то и не найти, работать будет,
        // но в List итоговом будут null-ы
        // Не снижал, так как в тестах такое не проверяется
        .toList();
  }
}
