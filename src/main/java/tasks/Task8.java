package tasks;

import common.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
    if (persons.size() == 0) {
      return Collections.emptyList();
    }
    // Тут удаление первого элемента происходило за О(n).
    List<String> ans = new ArrayList<>(persons.size() - 1);
    for (int i = 1; i < persons.size(); i++) {
      ans.add(persons.get(i).getFirstName());
    }
    return ans;
  }

  //сет сам сохранит только различнве значения
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //кажется тут был лишний третий блок
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }
    return result;
  }

  // тут вроде все хорошо, не понятно только почему размер 1. Ну и было бы наверное правильней брать последнее имя указанное пользователем, а не первое. 
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>(persons.size());
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), convertPersonToString(person));
      }
    }
    return map;
  }

  // Решение происходит полным перебором за O(n^2), хотя с этим можно разобраться за O(n) 
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Integer> persons1_Ids = new HashSet<>();
    for (Person person1 : persons1) {
      persons1_Ids.add(person1.getId());
    }
    for (Person person2 : persons2) {
      if (persons1_Ids.contains(person2.getId())) {
        return true;
      }
    }
    return false;
  }

  //стрим работал неправильно, что делает переменная count и зачем она нужна - загадка. Честно говоря не совсем понял, как это можно красиво исправить
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    List<Integer> h = numbers.filter(num -> num % 2 == 0).collect(Collectors.toList());
    count += h.size();
    return h.size();
  }
}
