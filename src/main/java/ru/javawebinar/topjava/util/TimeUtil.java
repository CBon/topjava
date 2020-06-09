package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private TimeUtil() {}

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }
}
