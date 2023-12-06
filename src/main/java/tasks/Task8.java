package tasks;

import common.Person;
import org.jetbrains.annotations.NotNull;

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

  private int count; // не слишком круто использовать ЛОНГ? - максимальное число ИНТ 2,4 млрд - треть всей планеты Земля

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames( @NotNull List<Person> persons) { // добалю-ка @NotNull и далее где могу
      return persons.stream()
          .skip(1)
          .map(Person::getFirstName)
          .collect(Collectors.toList());

    // persons.remove(0); зачем забивать время этой операцией? перестановка индексов - скип в стриме решает эту задачу


  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(@NotNull List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }
   // return getNames(persons).stream().distinct().collect(Collectors.toSet()); - не нужен distinct тк есть Set и сам стрим тоже лишний
  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(@NotNull Person person) {
   return Stream.of(
            person.getFirstName(),
            person.getSecondName(),
            person.getMiddleName())
        .collect(Collectors.joining(" "));
  }

/*
    String result = "";
    if (person.getSecondName() != null) {    - ЭТА СТРОКА ПОВТОРЯЕТ СТРОКУ СНИЗУ
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName(); - ЭТА СТРОКА ПОВТОРЯЕТ СТРОКУ СВЕРХУ
    }
    return result;
    */

  // словарь id персоны -> ее имя

  public Map<Integer, String> getPersonNames (@NotNull Collection<Person> persons) {
      return persons.stream()

          .collect(Collectors.toMap(
              person -> person.getId(),
                  person -> convertPersonToString(person),
                  (oldValue, newValue) -> oldValue));
      //смешно что позавчера ночью код такой же не получался, вдвойне стыдно что не ставил скобки в бифанкшн - поэтому и не писал такую версию кода
    }
    /*
    Map<Integer, String> map = new HashMap<>(1);
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
    return map;

     */


  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {


    // UPD - теперь как вариант 2 сета - отсечем дубликаты в каждой коллекции и будем искать пересечение

    return persons1.stream()
        .anyMatch(persons2::contains);
    // эхх жать тут нет отладчика - хочу посмотреть работает ли код

  }

  //...
  public long countEven(@NotNull  Stream<Integer> numbers) {
  // мне кажется тут есть КОНУТ для стримов - так читабельнее
    return  numbers
        .filter(num -> num % 2 == 0)
        .count();
  }
}