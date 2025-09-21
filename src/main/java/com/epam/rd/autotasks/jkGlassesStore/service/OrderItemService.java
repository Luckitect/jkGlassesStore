package com.epam.rd.autotasks.jkGlassesStore.service;

import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.OrderItem;
import com.epam.rd.autotasks.jkGlassesStore.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void deleteAll() {
        orderItemRepository.deleteAll();
    }

    public List<OrderItem> getByOrder(Order order){
        return orderItemRepository.findByOrder(order);
    }

}
