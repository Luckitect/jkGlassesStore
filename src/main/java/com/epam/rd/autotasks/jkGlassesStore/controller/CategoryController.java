package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Category;
import com.epam.rd.autotasks.jkGlassesStore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getByName(@PathVariable String name) {
        Optional<Category> category = categoryService.getCategoryByName(name);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Category> getByType(@PathVariable String type) {
        Optional<Category> category = categoryService.getCategoriesByType(type);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
