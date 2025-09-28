package com.epam.rd.autotasks.jkGlassesStore;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import com.epam.rd.autotasks.jkGlassesStore.controller.ProductController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class BrandControllerTest {


    @Autowired
    private BrandRepository brandRepository;
    @Test
    @Transactional
    void testGetAllBrands() {

        List<Brand> Brands = brandRepository.findAll();
        assert Brands.size() == 3;
    }

    @Test
    @Transactional
    void testDeleteBrand() {
        List<Brand> initialBrands = brandRepository.findAll();
        brandRepository.deleteAllById(Collections.singleton(1L));
        List<Brand> remainingBrands = brandRepository.findAll();
        assert remainingBrands.size() == 2;
    }

    @Test
    @Transactional
    void testCreateBrand() {
        Brand brand = new Brand();
        brand.setName("New Brand");
        assert brand.getName().equals("New Brand") : "Cant add brand";

    }

    @Test
    @Transactional
    void testUpdateBrand() {
        List<Brand> initialBrands = brandRepository.findAll();
        Brand brand = initialBrands.get(0);
        brand.setName("Updated Brand");
        brandRepository.save(brand);
        assert brand.getName().equals("Updated Brand") : "Cant upgrade";
    }
}
