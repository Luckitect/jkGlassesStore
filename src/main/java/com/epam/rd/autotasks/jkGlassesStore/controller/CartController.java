package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Cart;
import com.epam.rd.autotasks.jkGlassesStore.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.getCartByUser(userId);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
