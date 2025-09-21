package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.BlogPost;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.service.BlogPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BlogPost>> getPostsByAuthor(@PathVariable Long authorId) {
        User author = new User();
        author.setId(authorId);

        List<BlogPost> posts = blogPostService.getByAuthor(author);
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }
}
