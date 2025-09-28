package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.BlogPost;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.BlogPostRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogposts")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    public BlogPostController(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    // DTO
    public static class BlogPostDTO {
        public Long id;
        public String title;
        public String content;
        public Long authorId;
        public LocalDateTime createdAt;
    }

    private BlogPostDTO toDTO(BlogPost post) {
        BlogPostDTO dto = new BlogPostDTO();
        dto.id = post.getId();
        dto.title = post.getTitle();
        dto.content = post.getContent();
        dto.authorId = post.getAuthor().getId();
        dto.createdAt = post.getCreatedAt();
        return dto;
    }

    // Get all blog posts  მუშაობს
    @GetMapping
    public List<BlogPostDTO> getAll() {
        return blogPostRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get bog post by id მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> ResponseEntity.ok(toDTO(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create blog post  მუშაობს
    @PostMapping
    public ResponseEntity<BlogPostDTO> create(@RequestBody BlogPostDTO dto) {
        User author = userRepository.findById(dto.authorId).orElse(null);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        BlogPost post = new BlogPost();
        post.setTitle(dto.title);
        post.setContent(dto.content);
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());

        BlogPost saved = blogPostRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    // Update blog post მუშაობს
    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> update(@PathVariable Long id, @RequestBody BlogPostDTO dto) {
        return blogPostRepository.findById(id).map(post -> {
            post.setTitle(dto.title);
            post.setContent(dto.content);
            BlogPost updated = blogPostRepository.save(post);
            return ResponseEntity.ok(toDTO(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete blog post მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!blogPostRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        blogPostRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
