package tasks;

import common.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
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
    if (persons.size() <= 1) {
      return Collections.emptyList();
    }
    persons.remove(0); //  удаление происходит за O(n). Можно от этого избавиться если сделать через for, и выгрыш по времени будет. Но асимптотика от этого не поменяется
    return persons.stream().map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet()); // сет уже хранит уникальные элементы дистинкт не нужен
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }
    //Здесь два раза добавлялась фамилия
    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(Person::getId, p -> convertPersonToString(p)));
  }

  // тут была проверка за o(n*m). Все еще не оптимально, но короче
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons1.size() + persons2.size() > Stream.concat(persons1.stream(), persons2.stream()).map(Person::getId).collect(Collectors.toSet()).size();
  }
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> {count += 1;});
    return count;
  }
}
