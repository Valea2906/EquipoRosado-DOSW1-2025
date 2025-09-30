package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService service = new OrderService();
    Customer customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");
    Order order = new Order(1L, customer);

    @Test
    void testAgregarYBuscarPedido() {
        service.addOrder(order);
        assertEquals(order, service.findOrderById(1L), "Debe encontrar el pedido agregado");
        assertNull(service.findOrderById(2L), "No debe encontrar un pedido inexistente");
    }

    @Test
    void testObtenerTodosLosPedidos() {
        service.addOrder(order);
        assertEquals(1, service.getAllOrders().size(), "La lista de pedidos debe contener 1 pedido");
    }
}