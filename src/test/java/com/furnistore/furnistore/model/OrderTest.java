package com.furnistore.furnistore.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class OrderTest {

    Customer customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");
    Product sofa = new Product(1L, "Sofá Clásico", "Sofá", "Clásico", 500.0, 10) {};
    Order order = new Order(1L, customer);

    @Test
    void testAgregarProductoYCalcularTotal() {
        order.addProduct(sofa, 2);
        assertEquals(2, order.getProducts().size(), "El pedido debería tener 2 productos");
        assertEquals(1000.0, order.getTotal(), "El total del pedido debe calcularse correctamente");
        assertEquals(8, sofa.getStockQuantity(), "El stock del producto debe disminuir correctamente");
    }

    @Test
    void testActualizarEstado() {
        order.updateStatus("Enviado");
        assertEquals("Enviado", order.getStatus(), "El estado del pedido debe actualizarse correctamente");
    }
}
