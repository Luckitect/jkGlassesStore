package com.epam.rd.autotasks.jkGlassesStore.repository;

import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.model.Rating;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByProduct(Product product);
    List<Rating> findByUser(User user);
}
