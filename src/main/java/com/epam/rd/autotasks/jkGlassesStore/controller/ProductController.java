package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDetails.getName());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setSize(productDetails.getSize());
                    existingProduct.setColor(productDetails.getColor());
                    existingProduct.setMaterial(productDetails.getMaterial());
                    existingProduct.setPolarized(productDetails.isPolarized());
                    existingProduct.setStock(productDetails.getStock());
                    existingProduct.setSale(productDetails.isSale());
                    existingProduct.setCategory(productDetails.getCategory());
                    existingProduct.setBrand(productDetails.getBrand());

                    Product updated = productService.saveProduct(existingProduct);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 if product doesn’t exist
        }
    }

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


//Get all products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    // Get products by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Get product by brand
    @PostMapping("/brand/{brand}")
    public List<Product> getProductByBrand(@PathVariable String brand){
        return productService.getProductByBrand(brand);
    }

    //Get Product by price range
    @PostMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam double min,  @RequestParam double max) {
        return productService.getProductByPriceRange(min, max);
    }

    @PostMapping("/sale")
    public List<Product> getProductsOnSale() {
        return productService.getProductsOnSale();
    }

    @GetMapping("/polarized")
    public List<Product> getPolarizedProducts() {
        return productService.getPolarizedProducts();
    }

    @GetMapping("/size/{size}")
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getBySize(size);
    }


    @GetMapping("/color/{color}")
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getByColor(color);
    }

    @GetMapping("/material/{material}")
    public List<Product> getProductsByMaterial(@PathVariable String material) {
        return productService.getByMaterial(material);
    }

    @GetMapping("/available")
    public List<Product> getProductsIfAvailable() {
        return productService.getIfAvailable();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }

}
