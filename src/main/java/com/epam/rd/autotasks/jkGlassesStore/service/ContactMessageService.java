package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.repository.CategoryRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import com.epam.rd.autotasks.jkGlassesStore.model.*;

import java.util.List;

@Service
public class ContactMessageService {
    private final ContactMessageRepository contactMessageRepository ;

    public ContactMessageService(CategoryRepository categoryRepository, ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public List<ContactMessage> getMessagesByEmail(String email) {
        return contactMessageRepository.findByEmail(email);
    }




}
