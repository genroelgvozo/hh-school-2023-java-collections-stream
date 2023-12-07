package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

/*
асимптотика n*ln(n)
 */
public class Task3 {
  public static Integer Compare(Person p1, Person p2) {
    if (p1.getSecondName().compareTo(p2.getSecondName()) == 0) {
        if (p1.getFirstName().compareTo(p2.getFirstName()) == 0) {
          return p1.getCreatedAt().compareTo(p2.getCreatedAt());
        } 
        else {
          return p1.getFirstName().compareTo(p2.getFirstName());
        }
    } 
    else {
        return p1.getSecondName().compareTo(p2.getSecondName());
    }
  }

  public static List<Person> sort(Collection<Person> persons) {
    List<Person> ans = new ArrayList<>(persons.size());
    ans.addAll(persons);
    Collections.sort(ans, (p1, p2) -> Compare(p1, p2));
    return ans;
  }
}
