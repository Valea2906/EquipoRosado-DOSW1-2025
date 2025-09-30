package com.furnistore.furnistore.controller;


import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.service.OrderService;

public class OrderController {

    private OrderService orderService = new OrderService();

    public void addOrder(Order order) {
        orderService.addOrder(order);

    }
    public void listOrders() {
        orderService.getAllOrders().forEach(o ->
                System.out.println("Order: " + o.getId() + ", Customer: " + o.getCustomer().getName() + ", Total: " + o.getTotal())
        );
    }
}
