package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
        mealsTo.forEach(System.out::println);

//      System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // временная сложность алгоритма, Time complexity = O(2N)
        List<UserMealWithExcess> resultList = new LinkedList<>();
        List<UserMeal> selectedList = new LinkedList<>();
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        for (UserMeal userMeal : meals
        ) {
            // проверяем, что попадаем в указанный диапазон времени
            LocalTime localTime = userMeal.getDateTime().toLocalTime();
            if (localTime.isBefore(endTime))
                if (localTime.compareTo(startTime) >= 0) {
                    // накапливаем сумму калорий за день
                    caloriesPerDayMap.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
                    // помещаем выбранные записи в отдельный список
                    selectedList.add(userMeal);
                }
        }
        // теперь, когда все суммы калорий посчитаны,
        // создаем записи для итогового списка с индикатором превышения 'excess'
        for (UserMeal userMeal : selectedList
        ) {
            // Создаем экземпляр с указанием превышения нормы
            UserMealWithExcess userMealWithExcess = new UserMealWithExcess(
                    userMeal.getDateTime(),
                    userMeal.getDescription(),
                    userMeal.getCalories(),
                    caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay);
            // вставляем в итоговый список
            resultList.add(userMealWithExcess);
        }
        return resultList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // выбираем строки за указанное время
        List<UserMeal> filteredList = meals.stream()
                .filter(s -> s.getDateTime().toLocalTime().isBefore(endTime)
                        && s.getDateTime().toLocalTime().compareTo(startTime) >= 0)
                .collect(Collectors.toList());

        // получаем суммы калорий
        Map<LocalDate, Integer> localDateIntegerMap = filteredList.stream()
                .collect(Collectors.groupingBy((s) -> s.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        // формируем объекты для результирующего списка
        return filteredList.stream()
                .collect(
                        ArrayList::new, // создаем результирующий список
                        (list, item) -> {                           // создаем новый объект
                            boolean excess = localDateIntegerMap.get(item.getDateTime().toLocalDate()) > caloriesPerDay;
                            UserMealWithExcess userMealWithExcess = new UserMealWithExcess(
                                    item.getDateTime(),
                                    item.getDescription(),
                                    item.getCalories(),
                                    excess);
                            list.add(userMealWithExcess);
                        },
                        ArrayList::addAll);
    }
}