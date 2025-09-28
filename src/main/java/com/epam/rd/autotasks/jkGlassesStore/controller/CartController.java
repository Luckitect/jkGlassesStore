package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.CartDTO;
import com.epam.rd.autotasks.jkGlassesStore.dto.CartItemDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Cart;
import com.epam.rd.autotasks.jkGlassesStore.model.CartItem;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.UserRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import com.epam.rd.autotasks.jkGlassesStore.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(CartService cartService,
                          UserRepository userRepository,
                          ProductRepository productRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Convert CartItem -> DTO
    private CartItemDTO toCartItemDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    // Convert Cart -> DTO
    private CartDTO toCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setUserName(cart.getUser().getFirstName() + " " + cart.getUser().getLastName());

        List<CartItemDTO> items = cart.getItems() != null
                ? cart.getItems().stream().map(this::toCartItemDTO).collect(Collectors.toList())
                : List.of();
        dto.setItems(items);

        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        dto.setTotalPrice(total);

        return dto;
    }

    //get cart from users მუშაობს
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUser(userId)
                .map(this::toCartDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //create for user მუშაობს
    @PostMapping("/user/{userId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(cartService::createCart)
                .map(this::toCartDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
