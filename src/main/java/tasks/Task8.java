package tasks;

import common.Person;

import java.util.*;
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
  private static final int N_FAKES = 1;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  // Изменять данные плохо
  public List<String> getNames(List<Person> persons) {
    return persons.stream().skip(N_FAKES).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  // При создании Set повторяющиеся элементы пропадут, и можно использовать конструктор HashSet, чтобы не создавать
  // новый stream
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // Можно использовать StringBuilder, чтобы в каждом if не создавать новую строку
  public String convertPersonToString(Person person) {
    StringBuilder result = new StringBuilder();

    if (person.getSecondName() != null) {
      result.append(person.getSecondName());
    }

    if (person.getFirstName() != null) {
      result.append(person.getFirstName());
    }

    if (person.getSecondName() != null) {
      result.append(person.getSecondName()); // Возможно, опечатка - зачем два раза печатать SecondName?
    }

    return result.toString();
  }

  // словарь id персоны -> ее имя
  // Можно использовать stream
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString, (p1, p2) -> p1));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  // Можно использовать метод disjoint
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return !Collections.disjoint(persons1, persons2);
  }

  //...
  // Можно использовать stream
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(n -> n % 2 == 0).count();
  }
}
