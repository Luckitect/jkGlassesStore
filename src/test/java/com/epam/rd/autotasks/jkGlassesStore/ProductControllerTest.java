package com.epam.rd.autotasks.jkGlassesStore;

import static org.junit.jupiter.api.Assertions.*;
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
public class ProductControllerTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    @Transactional //ამის გარეშე ესენი დაფეილდებოდნენ, sql ს არესეტებს
    void testGetAllProducts() {


        List<Product> products = productRepository.findAll();
        System.out.println(products.size());
        assert products.size() == 3;
        assert products.get(0).getName().equals("Aviator Glasses") : "Aviator Glasses not found";
        assert products.get(1).getName().equals("Round Glasses") : "Round Glasses not found";
        assert products.get(2).getName().equals("Aviator") : "Aviator not found";

        assert products.get(0).getPrice() == 150.0 : " Glasses price is not 150";
        assert products.get(1).getPrice() == 120.0 : " Glasses price is not 120";
        assert products.get(2).getPrice() == 150.0 : " Glasses price is not 150";

        assert Objects.equals(products.get(0).getSize(), "M") : "Aviator Glasses size is not M";
        assert Objects.equals(products.get(1).getSize(), "L") : "Batman Glasses size is not L";
        assert Objects.equals(products.get(2).getSize(), "M") : "Superman Glasses size is not XL";


        assert products.get(0).isPolarized() : "Aviator Glasses should be polarized";
        assert !products.get(1).isPolarized() : "Round Glasses should not be polarized";
        assert products.get(2).isPolarized() : "Aviator should be polarized";

        assert  products.get(0).getCategory().getId() == 1 : "Aviator Glasses category is not 1";
        assert  products.get(1).getCategory().getId() == 2 : "Round Glasses category is not 2";
        assert  products.get(2).getCategory().getId() == 1 : "Aviator category is not 3";
    }

    @Test
    @Transactional

    void testDeleteProduct() {

        List<Product> initialProducts = productRepository.findAll();

        cartItemRepository.deleteAllById(Collections.singleton(1L));
        orderItemRepository.deleteAllById(Collections.singleton(1L));
        ratingRepository.deleteAllById(Collections.singleton(1L)); // ესენი იმიტო ჭრდება რო თუ წავშლი რამე პროდუქტს, სხვა რეპოებიდან არის ამ პროდუქტთან რეფერენსი
        productRepository.deleteById(1L);


        List<Product> remainingProducts = productRepository.findAll();
        assertEquals(2, remainingProducts.size(), "Product count after deletion mismatch");
        assertTrue(remainingProducts.stream().noneMatch(p -> p.getId() == 1L), "Deleted product still exists");
    }


    @Test
    @Transactional
    void testUpdateProduct() {
        List<Product> initialProducts = productRepository.findAll();

        Product product = initialProducts.get(0);
        product.setName("Updated Aviator Glasses");
        product.setPrice(100.0);
        product.setSize("S");
        product.setPolarized(false);
        product.setCategory(initialProducts.get(0).getCategory());

        productRepository.save(product);


        assert product.getName().equals("Updated Aviator Glasses") : "Cant upgrade";
        assert product.getPrice() == 100.0 : "Cant upgrade price";
        assert product.getSize().equals("S") : "Cant upgrade size";
        assert !product.isPolarized() : "Cant upgrade polarized";
    }

    @Test
    @Transactional
    void testCreateProduct() {
        Product product = new Product();
        product.setName("New Aviator Glasses");
        product.setPrice(100.0);
        product.setSize("S");
        product.setPolarized(false);

        assert product.getName().equals("New Aviator Glasses") : "Cant add product";
        assert product.getPrice() == 100.0 : "Cant add product, price";
        assert product.getSize().equals("S") : "Cant add product, size";
        assert !product.isPolarized() : "Cant add product, polarized";
    }



    
    
}
