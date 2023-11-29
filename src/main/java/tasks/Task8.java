package tasks;

import common.Person;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

  // я бы добавил @NotNull -  Сергей Борискин
  public List<String> getNames( @NotNull List<Person> persons) {

     // я б указал в стриме что бы по нулевому индексу элемент был удален  - Сергей Борискин
     // просто вмерсто проверки и удаления возвращаем skip - Сергей Борискин

    return persons.stream()
            .skip(1)
            .map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {

    // А зачем тут distinct если сам Сет не даст положить одинаковые имена? убираем его -  Сергей Борискин
    return getNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут

  // и вот тут дал бы Нот нул -  Сергей Борискин
  public String convertPersonToString( @NotNull Person person) {
    String result = person.getFirstName() +  person.getSecondName() +  person.getMiddleName();

    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>();
// зачем так сложно если есть стрим - СБ и зачем указывать изначально в мапе кол-во 1? если вернет пустой стрим - чем плохо?
    map = persons.stream()
            .collect(Collectors.toMap(
                    x -> x.getId(),
                    x -> x.getFirstName() + " " + x.getSecondName()+ " " + x.getMiddleName())); // ну имя тоже хз какое - пусть будет ФИО (лишнее удалят через сплит-пробел)
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // вот тут я бы оставил все как есть - в стримы двойной вызов писать как я вижу будет сложно - тем более
    //встраивать туда counter
    boolean has = false;
    for (Person person1 : persons1) {
      for (Person person2 : persons2) {
        if (person1.equals(person2)) {
          count++;// - я бы счетчик добавил сюда - если код ниже к этой задаче (смутили ... - полагаю это одно задание
          // и весь код снизу не нужен или его уодвить на 2 для количества персон а не пар(значений) )
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
