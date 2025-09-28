package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Rating;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.repository.RatingRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.UserRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public RatingController(RatingRepository ratingRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // DTO
    public static class RatingDTO {
        public Long id;
        public int starsNum;  // renamed
        public String comment;
        public Long userId;
        public Long productId;
    }

    private RatingDTO toDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.id = rating.getId();
        dto.starsNum = rating.getStarsNum(); // renamed
        dto.comment = rating.getComment();
        dto.userId = rating.getUser().getId();
        dto.productId = rating.getProduct().getId();
        return dto;
    }

    // Get all ratings მუშაობს
    @GetMapping
    public List<RatingDTO> getAll() {
        return ratingRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Get rating by id მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getById(@PathVariable Long id) {
        return ratingRepository.findById(id)
                .map(r -> ResponseEntity.ok(toDTO(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create rating მუშაობს
    @PostMapping
    public ResponseEntity<RatingDTO> create(@RequestBody RatingDTO dto) {
        User user = userRepository.findById(dto.userId).orElse(null);
        Product product = productRepository.findById(dto.productId).orElse(null);

        if (user == null || product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Rating rating = new Rating();
        rating.setStarsNum(dto.starsNum);  // renamed
        rating.setComment(dto.comment);
        rating.setUser(user);
        rating.setProduct(product);

        Rating saved = ratingRepository.save(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    // Update rating მუშაობს
    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> update(@PathVariable Long id, @RequestBody RatingDTO dto) {
        return ratingRepository.findById(id).map(rating -> {
            rating.setStarsNum(dto.starsNum); // renamed
            rating.setComment(dto.comment);
            Rating updated = ratingRepository.save(rating);
            return ResponseEntity.ok(toDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete rating მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!ratingRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ratingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
