package com.epam.rd.autotasks.jkGlassesStore.mapper;

import com.epam.rd.autotasks.jkGlassesStore.dto.*;
import com.epam.rd.autotasks.jkGlassesStore.model.*;
import java.util.List;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());

        List<OrderDTO> orders = user.getOrders().stream().map(order -> {
            OrderDTO o = new OrderDTO();
            o.setOrderId(order.getOrderId());
            o.setOrderDate(order.getOrderDate());
            o.setStatus(order.getStatus());
            List<OrderItemDTO> items = order.getItems().stream().map(item -> {
                OrderItemDTO i = new OrderItemDTO();
                i.setId(item.getId());
                i.setQuantity(item.getQuantity());
                i.setPrice(item.getPrice());
                return i;
            }).toList();
            o.setItems(items);
            return o;
        }).toList();

        dto.setOrders(orders);
        return dto;
    }
}
