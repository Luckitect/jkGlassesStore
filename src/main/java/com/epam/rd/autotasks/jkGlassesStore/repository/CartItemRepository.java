package com.epam.rd.autotasks.jkGlassesStore.repository;

import com.epam.rd.autotasks.jkGlassesStore.model.Cart;
import com.epam.rd.autotasks.jkGlassesStore.model.CartItem;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);

}
