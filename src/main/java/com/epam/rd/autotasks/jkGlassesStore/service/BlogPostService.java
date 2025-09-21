package com.epam.rd.autotasks.jkGlassesStore.service;

import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;
import java.util.List;


@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getByAuthor(User author) {
        return blogPostRepository.findByAuthor(author);
    }

}
