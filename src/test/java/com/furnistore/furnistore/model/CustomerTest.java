package com.furnistore.furnistore.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {

        customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");
    }

    @Test
    void testGetters() {

        assertEquals(1L, customer.getId(), "El id del cliente debe ser correcto");
        assertEquals("Ana", customer.getName(), "El nombre del cliente debe ser correcto");
        assertEquals("ana@email.com", customer.getEmail(), "El email del cliente debe ser correcto");
        assertEquals("3001234567", customer.getPhone(), "El teléfono del cliente debe ser correcto");
    }
}
