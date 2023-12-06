package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/*
Асимптотика: O( n * ln(n) )
 */
public class Task2 {
  
  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    Integer n = persons1.size() + persons2.size();
    List<Person> merged_people = new ArrayList<>(n);
    merged_people.addAll(persons1);
    merged_people.addAll(persons2);
    Comparator<Person> time =(person1, person2) -> person1.getCreatedAt().compareTo(person2.getCreatedAt());
    Collections.sort(merged_people, time);
    return merged_people.subList(0, Math.min(limit, n));
  }
}
