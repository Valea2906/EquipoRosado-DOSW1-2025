package com.furnistore.furnistore.repository;


import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByStatus(String status);
}
