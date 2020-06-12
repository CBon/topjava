package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryRepository implements Repository<Meal> {
    final private Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    final static private AtomicInteger sequenceId = new AtomicInteger(0);
    final static List<Meal> meals = Collections.unmodifiableList(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));
    public InMemoryRepository() {
       this(meals);
    }

    public InMemoryRepository(List<Meal> meals) {
        meals.forEach(this::save);
    }

    @Override
    public void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(sequenceId.getAndIncrement());
        }
        mealsMap.put(meal.getId(), meal);
    }

    @Override
    public void delete(int Id) {
        mealsMap.remove(Id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealsMap.values();
    }

    @Override
    public Meal get(int Id) {
        return mealsMap.get(Id);
    }
}
