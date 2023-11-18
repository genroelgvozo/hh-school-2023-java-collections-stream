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

  //Можно удалить if, но в таком случае возвращаемый пустой список будет изменяемым, поэтому на всякий случай оставил
  //Использовал toList() как более простой вариант создания списка
  //Изменил условие if на более понятное
  public List<String> getNames(List<Person> persons) {
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    return persons.stream().skip(1).map(Person::getFirstName).toList();
  }

  //используем православный способ преобразования листа к сету
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Исправил опечатку, из-за которой в результате не было отчества
  //Не понятно в каком порядке выводить, поэтому вывожу ФИО
  //Избавился от повторяющегося кода с помощью Stream API
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
        .filter(Objects::nonNull)
        .collect(Collectors.joining(" "));
  }

  //Уменьшил размер и повысил читаемость кода с помощью Stream API
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(
        Person::getId,
        this::convertPersonToString,
        (a, b) -> a)
    );
  }

  //Уменьшил время работы с O(n * m) до O(n + m)
  //Использовал StreamAPI для сокращения количества кода
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons2.stream().anyMatch((new HashSet<>(persons1))::contains);
  }

  //Не понятно зачем использовать переменную объекта класса для count. Так как она приватная и нет геттеров убрал её
  //Кажется странным передача стрима как аргумента, мы не можем его скопировать (как я поискал в гугле), поэтому приходится его использовать и соответственно изменять
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
