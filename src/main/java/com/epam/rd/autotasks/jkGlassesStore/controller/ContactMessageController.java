package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.ContactMessage;
import com.epam.rd.autotasks.jkGlassesStore.repository.ContactMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-messages")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class ContactMessageController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }


    //Create new messages მუშაობს
    @PostMapping
    public ResponseEntity<ContactMessage> createMessage(@RequestBody ContactMessage message) {
        ContactMessage saved = contactMessageRepository.save(message);
        return ResponseEntity.ok(saved);
    }

    //Get all massages მუშაობს
    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        List<ContactMessage> messages = contactMessageRepository.findAll();
        return ResponseEntity.ok(messages);
    }


}
