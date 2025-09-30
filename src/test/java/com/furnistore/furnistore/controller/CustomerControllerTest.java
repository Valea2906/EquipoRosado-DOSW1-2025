package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerControllerTest {

    private CustomerController customerController;
    private Customer customer;

    @BeforeEach
    void setUp() {

        customerController = new CustomerController();
        customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");
    }

    @Test
    void testAddCustomer() {

        customerController.addCustomer(customer);
    }

    @Test
    void testListCustomers() {

        customerController.addCustomer(customer);
        customerController.listCustomers();
    }
}
