package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.mapper.ProductMapper;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // Get all products მუშაობს
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }


    // Get a single product by ID მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductMapper.toDTO(optionalProduct.get()));
    }

    // Get products by brand მუშაობს
    @GetMapping("/brand/{brandId}")
    public List<ProductDTO> getProductsByBrandId(@PathVariable Long brandId) {
        return productService.getProductsByBrandId(brandId);
    }

    // Get products by category მუშაობს
    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductDTOsByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }
    //მუშაობს
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
        return productService.createProduct(dto);
    }

    // Update product by ID მუშაობს
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct
    ) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        // Update fields from JSON
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        // add other fields if needed

        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    // Delete product by ID მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }



}
