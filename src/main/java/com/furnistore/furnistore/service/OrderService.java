package com.furnistore.furnistore.service;


import com.furnistore.furnistore.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) { orders.add(order); }

    public List<Order> getAllOrders() { return orders; }

    public Order findOrderById(Long id) {
        return orders.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
    }
}
