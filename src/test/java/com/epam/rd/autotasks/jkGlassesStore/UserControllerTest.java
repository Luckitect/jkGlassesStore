package com.epam.rd.autotasks.jkGlassesStore;

import static org.junit.jupiter.api.Assertions.*;
import com.epam.rd.autotasks.jkGlassesStore.model.Product;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.*;
import com.epam.rd.autotasks.jkGlassesStore.service.ProductService;
import com.epam.rd.autotasks.jkGlassesStore.controller.ProductController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void testGetAllUser() {

        List<User> users = userRepository.findAll();

        assert users.size() == 3;
        assert users.get(0).getFirstName().equals("Jokola") : "Jokola doesn't match on that user";
        System.out.println(users.get(0).getFirstName());

    }

    @Test
    @Transactional
    void testDeleteUser() {
        List<User> initialUsers = userRepository.findAll();
        userRepository.deleteAllById(Collections.singleton(1L));

        List<User> remainingUsers = userRepository.findAll();

        assert remainingUsers.size() == 2;
    }

    @Test
    @Transactional
    void testUpdateUser() {
        List<User> initialUsers = userRepository.findAll();

        User user = initialUsers.get(0);
        user.setFirstName("Updated Jokola");
        user.setLastName("Updated Kistauri");

        userRepository.save(user);

        assert user.getFirstName().equals("Updated Jokola") : "Cant upgrade";
        assert user.getLastName().equals("Updated Kistauri") : "Cant upgrade";
    }

    @Test
    @Transactional
    void testCreateUser() {
        User user = new User();
        user.setFirstName("New Jokola");
        user.setLastName("New Kistauri");

        assert user.getFirstName().equals("New Jokola") : "Cant add user";
        assert user.getLastName().equals("New Kistauri") : "Cant add user last name";
        assert user.getEmail().equals("") : "Cant add user mail";
    }
}
