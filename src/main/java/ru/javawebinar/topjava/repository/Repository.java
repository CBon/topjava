package ru.javawebinar.topjava.repository;

import java.util.Collection;

public interface Repository<T> {
    void save(T t);
    void delete(int Id);
    Collection<T> getAll();
    T get(int Id);
}
