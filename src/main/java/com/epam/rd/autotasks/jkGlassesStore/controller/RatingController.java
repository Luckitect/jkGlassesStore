package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Rating;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<Rating> ratings = ratingService.getByUser(user);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getByProduct(@PathVariable Long productId) {
        Product product = new Product();
        product.setProductId(productId);
        List<Rating> ratings = ratingService.getByProduct(product);
        return ResponseEntity.ok(ratings);
    }
}
