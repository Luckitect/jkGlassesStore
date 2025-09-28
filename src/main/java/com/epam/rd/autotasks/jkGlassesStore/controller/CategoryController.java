package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.CategoryDTO;
import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Category;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Convert Product to DTO
    private ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setSize(product.getSize());
        dto.setColor(product.getColor());
        dto.setMaterial(product.getMaterial());
        dto.setPolarized(product.isPolarized());
        dto.setStock(product.getStock());
        dto.setSale(product.isSale());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }

    // Convert Category to DTO
    private CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setProducts(
                category.getProducts().stream()
                        .map(this::toProductDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    // Get all categories მუშაობს
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryRepository.findAll().stream()
                .map(this::toCategoryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    // Get category by ID მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(category -> ResponseEntity.ok(toCategoryDTO(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new category ვერ მუშაობს წესიერად, ერორ 500 აგდებს მარა სვამს მაინც
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category saved = categoryRepository.save(category);
        return ResponseEntity.ok(toCategoryDTO(saved));
    }

    // Delete category by ID მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
