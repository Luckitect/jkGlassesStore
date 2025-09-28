package com.epam.rd.autotasks.jkGlassesStore;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.rd.autotasks.jkGlassesStore.model.Brand;
import com.epam.rd.autotasks.jkGlassesStore.model.Cart;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
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
public class CartControllerTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository UserRepository;

    @Test
    @Transactional
    void testGetAllCart() {
        List<Cart> cart = cartRepository.findAll();

        assert cart.size() == 2;
    }

    @Test
    @Transactional
    void testDeleteCart() {
        List<Cart> initialCart = cartRepository.findAll();
        cartRepository.deleteAllById(Collections.singleton(1L));

        List<Cart> remainingCart = cartRepository.findAll();

        assert remainingCart.size() == 1;
    }
    
    @Test
    @Transactional
    void testUpdateCart() {
        List<Cart> initialCart = cartRepository.findAll();
        
        Cart newCart = cartRepository.findById(1L).get();
        newCart.setUser(UserRepository.findById(2L).get());

        List<Cart> newCarts = cartRepository.findAll();
        assert newCart.getUser().getId() == 2;
    }


    //Assign new cart to the third user
    @Test
    @Transactional
    void testCreateCart() {
        User thirdUser = UserRepository.findById(3L).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Cart newCart = new Cart();
        newCart.setUser(thirdUser);

        Cart savedCart = cartRepository.save(newCart);

        assertNotNull(savedCart, "Saved cart should not be null");
        assertEquals(3L, savedCart.getUser().getId(), "Cart should be assigned to the third user");
    }

}
