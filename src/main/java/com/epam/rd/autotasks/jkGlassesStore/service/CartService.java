package com.epam.rd.autotasks.jkGlassesStore.service;

import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> getCartByUser(Long userId) {
        return cartRepository.findByUser_Id(userId);
    }


}
