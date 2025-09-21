package com.epam.rd.autotasks.jkGlassesStore.repository;

import com.epam.rd.autotasks.jkGlassesStore.model.BlogPost;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthor(User user);

}
