package tasks;

import common.Company;
import common.Vacancy;
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
        .map(Company::getVacancies)
        .flatMap(Collection::stream)
        .map(Vacancy::getTitle)
        .collect(Collectors.toSet());
    // задачка на flatMap, не более. У меня разбито чтобы только ссылки на методы, но и .flatMap(company -> company.getVacancies().stream) тоже ок
  }

}
