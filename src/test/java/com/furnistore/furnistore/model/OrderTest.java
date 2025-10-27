package com.furnistore.furnistore.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class Chair extends Product {
    public Chair(Long id, String name, String category, String style, Double price, int stockQuantity) {
        super(id, name, category, style, price, stockQuantity);
    }
}

public class OrderTest {

    private Customer customer;
    private Product product;
    private Order order;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1L, "Carlos Pérez", "carlos@example.com", "3001112233");

        product = new Chair(1L, "Silla ergonómica", "Oficina", "Moderna", 500.0, 10);
        order = new Order(1L, customer);
    }

    @Test
    public void testAddProductAndCalculateTotal() {
        order.addProduct(product, 2);
        assertEquals(1, order.getItems().size());
        assertEquals(1000.0, order.getTotal());
        assertEquals(8, product.getStockQuantity());
    }

    @Test
    public void testUpdateStatus() {
        order.updateStatus("Shipped");
        assertEquals("Shipped", order.getStatus());
    }

    @Test
    public void testOrderInitialization() {
        assertEquals("Pending", order.getStatus());
        assertEquals(0.0, order.getTotal());
        assertEquals(customer, order.getCustomer());
        assertTrue(order.getItems().isEmpty());
    }
}
