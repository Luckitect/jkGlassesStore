package com.epam.rd.autotasks.jkGlassesStore;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.rd.autotasks.jkGlassesStore.dto.ProductDTO;
import com.epam.rd.autotasks.jkGlassesStore.mapper.ProductMapper;
import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Category;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.CartItemRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.OrderItemRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.RatingRepository;
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
public class ProductMapperTest {


    @Test
    void testToDTOWithBrandAndCategory() {

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("New Brand");

        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setBrand(brand);
        product.setCategory(category);

        ProductDTO dto = ProductMapper.toDTO(product);

        assert dto.getBrandId() == 1L;
        assert dto.getCategoryId() == 1L;
    }
}
