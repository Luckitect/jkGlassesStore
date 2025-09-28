package com.epam.rd.autotasks.jkGlassesStore;


import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.mapper.ProductMapper;
import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.BrandRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    void testGetProductByBrandId(){
        Brand brand = new Brand();
        brand.setName("New Brand");
        brandRepository.save(brand);

        Product product = new Product();
        product.setName("New Product");
        productRepository.save(product);

        List<ProductDTO> products = productService.getProductsByBrandId(brand.getId());

        assert products.size() == 4;
        assert products.get(4).getBrandId() == 4: "No match";


    }


}

