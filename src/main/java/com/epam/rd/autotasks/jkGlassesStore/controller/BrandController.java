package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.BrandDTO;
import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.BrandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // Convert Product to ProductDTO
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

    // Convert Brand to BrandDTO
    private BrandDTO toBrandDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setProducts(
                brand.getProducts().stream()
                        .map(this::toProductDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    // Get brand by ID მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id)
                .map(brand -> ResponseEntity.ok(toBrandDTO(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all brands მუშაობს
    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandRepository.findAll().stream()
                .map(this::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(brands);
    }

    // Create new brand მუშაობს, errors აგდებს მარა მაინც მუშაობს, არ ვიცი რატო
    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        Brand saved = brandRepository.save(brand);
        return ResponseEntity.ok(toBrandDTO(saved));
    }

    // Optional: Delete brand მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        if (!brandRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        brandRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: Update brand მუშაობს
    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return brandRepository.findById(id).map(brand -> {
            brand.setName(brandDTO.getName());
            Brand updated = brandRepository.save(brand);
            return ResponseEntity.ok(toBrandDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }
}
