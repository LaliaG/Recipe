package com.example.Recipe.controller;

import com.example.Recipe.model.Category;
import com.example.Recipe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category-list";
    }

    @GetMapping("/add")
    public String form(Model model){
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") Category category, BindingResult result){
        if(result.hasErrors()) {
            return "category-form";
        } else {
            if (category.getId() != null) {
                categoryService.update(category.getId(), category);
            } else {
                categoryService.create(category);
            }
        }
        return "redirect:/category/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id){
        categoryService.delete(id);
        return "redirect:/category/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") UUID id, Model model){
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "category-form";
    }



}
