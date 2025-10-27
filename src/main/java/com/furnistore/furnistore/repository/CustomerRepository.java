package com.furnistore.furnistore.repository;

import com.furnistore.furnistore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // JpaRepository ya proporciona métodos CRUD: save, findAll, findById, deleteById, etc.
}
