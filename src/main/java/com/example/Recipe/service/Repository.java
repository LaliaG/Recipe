package com.example.Recipe.service;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
    T create(T entity);
    List<T> getAll();
    void delete(UUID id);
    T getById(UUID id);
    T update(UUID id, T entity);





}
