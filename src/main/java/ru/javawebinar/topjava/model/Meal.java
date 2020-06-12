package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    public void setId(Integer id) {
        Id = id;
    }

    private Integer Id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Integer getId() {
        return Id;
    }

    public Meal(Integer Id, LocalDateTime dateTime, String description, int calories) {
        this.Id = Id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }
    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime,description,calories);
    }

    public boolean isNew() {
        return Id == null;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
