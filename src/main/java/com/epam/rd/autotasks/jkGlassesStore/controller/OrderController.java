package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<Order> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
}
