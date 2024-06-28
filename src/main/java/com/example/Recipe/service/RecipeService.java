package com.example.Recipe.service;

import com.example.Recipe.model.Category;
import com.example.Recipe.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService implements IRecipeService {

    private List<Recipe> recipes = new ArrayList<>();


    @Override
    public Recipe create(Recipe entity, Category category) {
        entity.setId(UUID.randomUUID());
        entity.setCategory(category);
        recipes.add(entity);
        return entity;
    }

    @Override
    public List<Recipe> getAll() {
        return recipes;
    }

    @Override
    public void delete(UUID id) {
        recipes.removeIf(recipe -> recipe.getId().equals(id));
    }

    @Override
    public Recipe getById(UUID id) {
        return recipes.stream().filter(recipe -> recipe.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Recipe update(UUID id, Recipe entity, Category category) {
        Recipe recipeExist = getById(id);
        if (recipeExist != null) {
            recipeExist.setName(entity.getName());
            recipeExist.setIngredients(entity.getIngredients());
            recipeExist.setInstructions(entity.getInstructions());
            recipeExist.setCategory(category);
        }
        return recipeExist;
    }





}
