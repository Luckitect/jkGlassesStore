package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.model.Rating;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getByUser(User user){
        return ratingRepository.findByUser(user);
    }

    public List<Rating> getByProduct(Product product){
        return ratingRepository.findByProduct(product);
    }
}
