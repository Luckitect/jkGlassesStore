package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.ContactMassage;
import com.epam.rd.autotasks.jkGlassesStore.service.ContactMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-messages")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ContactMassage>> getMessagesByEmail(@PathVariable String email) {
        List<ContactMassage> messages = contactMessageService.getMessagesByEmail(email);
        if (messages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messages);
    }
}
