package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.FAQ;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.service.FAQService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FAQController {

    private final FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<FAQ>> getByAuthor(@PathVariable Long authorId) {
        User author = new User();
        author.setId(authorId);
        List<FAQ> faqs = faqService.getByAuthor(author);
        return ResponseEntity.ok(faqs);
    }
}
