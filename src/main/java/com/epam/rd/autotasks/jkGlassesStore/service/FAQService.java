package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.model.FAQ;

import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.FAQRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQService {
    private final FAQRepository faqRepository;

    public FAQService(FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<FAQ> getByAuthor(User author){
        return faqRepository.findByAuthor(author);
    }
}