package tasks;

import common.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
    //Лучше пропустить первого человека в стриме, чем его удалять отдельно
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
    //Заменил использование дистинкта, на hashset, что предоставляет похожий функционал
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
        .filter(Objects::nonNull).collect(Collectors.joining(" "));
    //Укоротил код, вместе многих условий реализовал в стриме
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
        .collect(Collectors.toMap(Person::getId,
            this::convertPersonToString, (person1, person2) -> person1));
    //Изменение кода от цикла в стрим, для улучшения наглядности
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> differentPersons = new HashSet<>((persons1));
    return persons2.stream()
        .anyMatch(differentPersons::contains);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
