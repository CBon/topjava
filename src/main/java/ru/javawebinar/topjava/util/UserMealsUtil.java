package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static java.util.stream.Collectors.*;

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

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//      System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    // pretendes to Optional 2
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // gather total calories per day for all rows to map
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        // gather rows by time period
        List<UserMeal> selectedUserMealList = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            caloriesPerDayMap.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
            LocalTime localTime = userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetweenHalfOpen(localTime, startTime, endTime)) {
                selectedUserMealList.add(userMeal);
            }
        }
        // create records for result list with 'excess' indicator
        List<UserMealWithExcess> resultList = new ArrayList<>();
        for (UserMeal userMeal : selectedUserMealList) {
            UserMealWithExcess userMealWithExcess = new UserMealWithExcess(
                    userMeal.getDateTime(),
                    userMeal.getDescription(),
                    userMeal.getCalories(),
                    caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay);
            resultList.add(userMealWithExcess);
        }
        return resultList;
    }

    // Optional 2
    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return meals.stream()
                .collect(groupingBy(u -> u.getDateTime().toLocalDate()))
                .entrySet().stream()
                .flatMap(m -> m.getValue().stream()
                        .filter(u -> TimeUtil.isBetweenHalfOpen(u.getDateTime().toLocalTime(), startTime, endTime))
                        .map(u ->
                                new UserMealWithExcess(
                                        u.getDateTime(),
                                        u.getDescription(),
                                        u.getCalories(),
                                        m.getValue().stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay)))
                .collect(toList());
    }
}