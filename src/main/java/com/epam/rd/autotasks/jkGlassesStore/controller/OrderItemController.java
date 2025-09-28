package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.OrderItemDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.OrderItem;
import com.epam.rd.autotasks.jkGlassesStore.repository.OrderItemRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.OrderRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-items")
@CrossOrigin(origins = "*") //allowed to be called from other origins მაგალითად ფრონტენდმა რო გამოიძახოს, * არის wildcard
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemController(OrderItemRepository orderItemRepository,
                               OrderRepository orderRepository,
                               ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // === DTO mapping ===
    private OrderItemDTO toOrderItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        if (item.getOrder() != null) {
            dto.setOrderId(item.getOrder().getOrderId());
        }
        if (item.getProduct() != null) {
            dto.setProductId(item.getProduct().getId());
        }
        return dto;
    }

    // === Endpoints ===
//მუშაობს
    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> items = orderItemRepository.findAll().stream()
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    //მუშაობს
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id)
                .map(item -> ResponseEntity.ok(toOrderItemDTO(item)))
                .orElse(ResponseEntity.notFound().build());
    }
    //მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        if (!orderItemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
