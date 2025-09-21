package com.epam.rd.autotasks.jkGlassesStore.repository;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

}
