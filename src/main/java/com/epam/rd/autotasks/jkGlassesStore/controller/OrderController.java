package com.epam.rd.autotasks.jkGlassesStore.controller;

import com.epam.rd.autotasks.jkGlassesStore.dto.OrderDTO;
import com.epam.rd.autotasks.jkGlassesStore.dto.OrderItemDTO;
import com.epam.rd.autotasks.jkGlassesStore.dto.UserDTO;
import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.OrderItem;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import com.epam.rd.autotasks.jkGlassesStore.repository.OrderRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.UserRepository;
import com.epam.rd.autotasks.jkGlassesStore.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // === DTO mapping ===
    private OrderItemDTO toOrderItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    private OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setUser(toUserDTO(order.getUser()));

        List<OrderItemDTO> items = order.getItems().stream()
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());
        dto.setItems(items);

        return dto;
    }

    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        return dto;
    }

    // === Endpoints ===

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderRepository.findAll().stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok(toOrderDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new order for a user მუშაობს
    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        return userRepository.findById(userId)
                .map(user -> {
                    Order order = new Order();
                    order.setUser(user);
                    order.setOrderDate(orderDTO.getOrderDate());
                    order.setStatus(orderDTO.getStatus());


                    // Convert items
                    List<OrderItem> items = orderDTO.getItems().stream().map(itemDTO -> {
                        OrderItem item = new OrderItem();
                        item.setOrder(order);
                        item.setQuantity(itemDTO.getQuantity());
                        item.setPrice(itemDTO.getPrice());
                        // Correct product lookup
                        productRepository.findById(itemDTO.getId()).ifPresent(item::setProduct);
                        return item;
                    }).collect(Collectors.toList());

                    order.setItems(items);

                    Order saved = orderRepository.save(order);
                    return ResponseEntity.ok(toOrderDTO(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete order by ID  მუშაობს
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
