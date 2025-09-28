package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.mapper.ProductMapper;
import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Category;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.BrandRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.CategoryRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          BrandRepository brandRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    // Convert Product to DTO
    public ProductDTO toDTO(Product product) {
        return ProductMapper.toDTO(product);
    }

    // Get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get products by brand
    public List<ProductDTO> getProductsByBrandId(Long brandId) {
        return productRepository.findByBrandId(brandId).stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get products by category
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName).stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO dto) {
        // 1. Fetch brand by ID
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // 2. Fetch category by ID
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 3. Create new product
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setSize(dto.getSize());
        product.setColor(dto.getColor());
        product.setMaterial(dto.getMaterial());
        product.setPolarized(dto.isPolarized());
        product.setStock(dto.getStock());
        product.setSale(dto.isSale());
        product.setBrand(brand);
        product.setCategory(category);

        // 4. Save it
        Product savedProduct = productRepository.save(product);

        // 5. Convert to DTO and return
        return ProductMapper.toDTO(savedProduct);
    }

    // Optional: find product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

}
