package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;

/*
асимптотика O(n)
 */
public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    List<ApiPersonDto> ans = new ArrayList<>(persons.size());
    for (Person p : persons) {
      ans.add(this.personConverter.convert(p));
    }
    return ans;
  }
}
