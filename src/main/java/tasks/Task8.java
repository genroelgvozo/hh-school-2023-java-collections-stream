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

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  // Необязательно удалять первый элемент, можно указать стриму с какого элемента начать обработку
  public List<String> getNames(List<Person> persons) {

    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  // Здесь стрим использовать не нужно
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // Здесь можно использовать стрим для лаконичности
  public String convertPersonToString(Person person) {

    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  // Можно использовать stream
  // Емкость HashMap зачем то была равна единице 0_o
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {

    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString,
                    (existingName, newName) -> existingName
            ));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  // использовал disjoint(), чтобы не было двойного цикла
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {

    return !Collections.disjoint(persons1,persons2);
  }

  // здесь переменную count можно убрать
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0)
            .count();
  }
}
