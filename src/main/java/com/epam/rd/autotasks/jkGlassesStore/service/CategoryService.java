package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Optional<Category> getCategoriesByType(String type) {
        return categoryRepository.findByType(type);
    }
}
