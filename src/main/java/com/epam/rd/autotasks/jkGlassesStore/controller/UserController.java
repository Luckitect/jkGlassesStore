package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        Optional<User> user = userService.getByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        Optional<User> user = userService.getByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> getByPhone(@PathVariable String phone) {
        Optional<User> user = userService.getByPhone(phone);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
