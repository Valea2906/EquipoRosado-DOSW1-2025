package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private Long nextId = 1L;

    // Crear orden
    public Order addOrder(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        orders.add(order);
        return order;
    }

    // Listar todas las órdenes
    public List<Order> getAllOrders() {
        return orders;
    }

    // Buscar por ID
    public Order findOrderById(Long id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Eliminar orden (opcional)
    public boolean deleteOrder(Long id) {
        return orders.removeIf(o -> o.getId().equals(id));
    }
}
