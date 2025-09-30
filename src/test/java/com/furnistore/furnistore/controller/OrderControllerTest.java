package com.furnistore.furnistore.controller;
import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderControllerTest {

    private OrderController orderController;
    private Customer customer;
    private Order order;

    @BeforeEach
    void setUp() {
        orderController = new OrderController();
        customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");
        order = new Order(1L, customer);
    }

    @Test
    void testAddOrder() {
        orderController.addOrder(order);

    }

    @Test
    void testListOrders() {
        orderController.addOrder(order);
        orderController.listOrders();
    }
}
