package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> findOrderById(Long id) {
        return repository.findById(id);
    }

    public List<Order> findOrdersByCustomer(Customer customer) {
        return repository.findByCustomer(customer);
    }

    public boolean deleteOrder(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
