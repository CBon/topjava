package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100003,LocalDateTime.of(2020, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(100004, LocalDateTime.of(2020, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(100005, LocalDateTime.of(2020, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(100006, LocalDateTime.of(2020, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100007, LocalDateTime.of(2020, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(100008, LocalDateTime.of(2020, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
