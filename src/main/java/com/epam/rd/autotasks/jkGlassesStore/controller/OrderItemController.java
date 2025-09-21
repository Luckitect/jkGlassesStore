package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.OrderItem;
import com.epam.rd.autotasks.jkGlassesStore.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getByOrder(@PathVariable Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        List<OrderItem> items = orderItemService.getByOrder(order);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        orderItemService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
