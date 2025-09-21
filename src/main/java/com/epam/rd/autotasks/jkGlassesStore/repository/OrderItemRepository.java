package com.epam.rd.autotasks.jkGlassesStore.repository;

import com.epam.rd.autotasks.jkGlassesStore.model.Order;
import com.epam.rd.autotasks.jkGlassesStore.model.OrderItem;
import com.epam.rd.autotasks.jkGlassesStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
List<OrderItem> findByOrder(Order order);

}
