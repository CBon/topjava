package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final Integer Id;

    private final LocalDateTime dateTime;

    private final String description;

    public Integer getId() {
        return Id;
    }

    private final int calories;

    private final boolean excess;

    public MealTo(int Id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.Id = Id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public int getCalories() {
        return this.calories;
    }
}
