package com.epam.rd.autotasks.jkGlassesStore.service;

import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }



}
