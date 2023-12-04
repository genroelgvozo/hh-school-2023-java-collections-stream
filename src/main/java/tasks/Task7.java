package tasks;

import common.Company;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {

    return companies.stream()
        .flatMap(x-> x.getVacancies().stream()
            .map(y -> y.getTitle()))
        .collect(Collectors.toCollection(HashSet::new));

  }
}
