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
    if (persons.size() == 0) { return Collections.emptyList();} // поправлю код - 1 строка вполне читабельна и далее где могу

      return persons.stream()
          .skip(1)
          .map(Person::getFirstName)
          .collect(Collectors.toList());

    // persons.remove(0); зачем забивать время этой операцией? перестановка индексов - скип в стриме решает эту задачу


  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(@NotNull List<Person> persons) {return new HashSet<>(getNames(persons));}
   // return getNames(persons).stream().distinct().collect(Collectors.toSet()); - не нужен distinct тк есть Set и сам стрим тоже лишний


  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(@NotNull Person person) {

    String secondName = (person.getSecondName() == null) ? ""  :       person.getSecondName();
    String firstName =   (person.getFirstName() == null) ? ""  : " " + person.getFirstName();
    return  secondName + firstName;

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
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>(1);
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    boolean has = false;
    for (Person person1 : persons1) {
      for (Person person2 : persons2) {
        if (person1.equals(person2)) {
          has = true;
        }
      }
    }
    return has;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
    return count;
  }
}