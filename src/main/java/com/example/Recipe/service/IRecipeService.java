package com.example.Recipe.service;

import com.example.Recipe.model.Category;
import com.example.Recipe.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface IRecipeService {
    Recipe create(Recipe entity, Category category);
    List<Recipe> getAll();
    void delete(UUID id);
    Recipe getById(UUID id);
    Recipe update(UUID id, Recipe entity, Category category);
}
