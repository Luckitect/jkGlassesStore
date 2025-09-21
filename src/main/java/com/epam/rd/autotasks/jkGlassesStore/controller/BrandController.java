package com.epam.rd.autotasks.jkGlassesStore.controller;


import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.service.BrandService;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/{name}")
    public ResponseEntity<Brand> getBrandByName(@PathVariable String name) {
        Optional<Brand> brand = brandService.getBrandByName(name);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}







