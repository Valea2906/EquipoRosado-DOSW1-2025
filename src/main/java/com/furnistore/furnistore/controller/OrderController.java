package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // POST /ordenes → crear orden y factura
    @PostMapping("/ordenes")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order saved = orderService.addOrder(order);
        return ResponseEntity.ok(saved);
    }

    // GET /ordenes → listar todas las órdenes
    @GetMapping("/ordenes")
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // GET /ordenes/{id} → consultar una orden específica
    @GetMapping("/ordenes/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }
}
