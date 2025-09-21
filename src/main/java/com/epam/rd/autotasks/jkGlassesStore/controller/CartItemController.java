package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Cart;
import com.epam.rd.autotasks.jkGlassesStore.model.CartItem;
import com.epam.rd.autotasks.jkGlassesStore.service.CartItemService;
import com.epam.rd.autotasks.jkGlassesStore.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartService cartService;

    public CartItemController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItem>> getItemsByUserCart(@PathVariable Long userId) {
        Optional<Cart> cartOpt = cartService.getCartByUser(userId);
        if (cartOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CartItem> items = cartItemService.getByCart(cartOpt.get());
        return ResponseEntity.ok(items);
    }
}
