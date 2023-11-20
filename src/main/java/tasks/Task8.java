package tasks;

import common.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task8 {

  public List<String> getFirstNames(List<Person> persons) {
    if (persons == null || persons.isEmpty()) {
      return Collections.emptyList();
    }

    return persons.stream()
        .skip(1)
        .map(Person::getFirstName)
        .toList();
  }

  public Set<String> getUniqueFirstNames(List<Person> persons) {
    if (persons == null || persons.isEmpty()) {
      return Collections.emptySet();
    }

    return new HashSet<>(getFirstNames(persons));
  }

  public String getPersonFullName(Person person) {
    return Optional.ofNullable(person)
        .map(p -> Stream.of(p.getSecondName(), p.getFirstName(), p.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining()))
        .orElse("");
  }

  public Map<Integer, String> getPersonFullNames(Collection<Person> persons) {
    if (persons == null || persons.isEmpty()) {
      return Collections.emptyMap();
    }

    return persons.stream()
        .collect(Collectors.toMap(
            Person::getId,
            this::getPersonFullName,
            (p1, p2) -> p1)
        );
  }

  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    if (persons1 == null || persons2 == null) {
      return false;
    }

    return persons1.stream()
        .anyMatch(persons2::contains);
  }

  public long countEvenNumbers(Stream<Integer> numbers) {
    if (numbers == null) {
      throw new IllegalArgumentException("Numbers cannot be null");
    }

    return numbers
        .filter(Objects::nonNull)
        .filter(num -> num % 2 == 0)
        .count();
  }
}
