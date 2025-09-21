package com.epam.rd.autotasks.jkGlassesStore.service;

import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory_Name(category);
    }

    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand_Name(brand);
    }

    public List<Product> getProductByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsOnSale() {
        return productRepository.findBySaleTrue();
    }

    public List<Product> getPolarizedProducts() {
        return productRepository.findByPolarizedTrue();
    }

    public List<Product> getBySize(String size) {
        return productRepository.findBySize(size);
    }

    public List<Product> getByColor(String color) {
        return productRepository.findByColor(color);
    }

    public List<Product> getByMaterial(String material) {
        return productRepository.findByMaterial(material);
    }

    public List<Product> getIfAvailable(){
        return productRepository.findByStockGreaterThan(1);
    }

}
