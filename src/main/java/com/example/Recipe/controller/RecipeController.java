package com.example.Recipe.controller;

import com.example.Recipe.model.Category;
import com.example.Recipe.model.Recipe;
import com.example.Recipe.service.CategoryService;
import com.example.Recipe.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    @Autowired
    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("recipes", recipeService.getAll());
        return "recipe-list";
    }

    @GetMapping("/add")
    public String form(Model model){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("recipe", new Recipe());
        return "recipe-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("recipe") Recipe recette, BindingResult result){
        if(result.hasErrors()) {
            return "category-form";
        } else {
            Category category = categoryService.getById(UUID.fromString(recette.getCategoryId()));
            if (recette.getId() != null) {
                recipeService.update(recette.getId(), recette, category);
            } else {
                recipeService.create(recette, category);
                System.out.println(recette);
            }
        }
        return "redirect:/recipe/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id){
        recipeService.delete(id);
        return "redirect:/recipe/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") UUID id, Model model){
        Recipe recipe = recipeService.getById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categoryService.getAll());
        return "recipe-form";
    }






}
