package com.example.Recipe.service;

import com.example.Recipe.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements Repository<Category> {

    private List<Category> categories = new ArrayList<>();

    @Override
    public Category create(Category entity) {
        entity.setId(UUID.randomUUID());
        categories.add(entity);
        return entity;
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public void delete(UUID id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

    @Override
    public Category getById(UUID id) {
        return categories.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Category update(UUID id, Category entity) {
        Category categoryExist = getById(id);
        if (categoryExist != null) {
            categoryExist.setName(entity.getName());
            categoryExist.setDescription(entity.getDescription());
        }
        return categoryExist;
    }

}
