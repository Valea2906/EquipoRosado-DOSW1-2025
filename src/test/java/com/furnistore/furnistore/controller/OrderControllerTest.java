package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateOrder() throws Exception {
        Customer c = new Customer(1L, "María López", "maria@example.com", "3214569870");
        Order o = new Order(null, c);

        when(service.addOrder(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId(1L);
            return order;
        });

        mockMvc.perform(post("/api/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(o)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customer.name").value("María López"));
    }

    @Test
    void testGetAllOrders() throws Exception {
        Customer c = new Customer(1L, "María López", "maria@example.com", "3214569870");
        Order o = new Order(1L, c);
        when(service.getAllOrders()).thenReturn(List.of(o));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("Pending"));
    }

    @Test
    void testGetOrderByIdFound() throws Exception {
        Customer c = new Customer(1L, "María López", "maria@example.com", "3214569870");
        Order o = new Order(1L, c);

        when(service.findOrderById(1L)).thenReturn(Optional.of(o));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    void testGetOrderByIdNotFound() throws Exception {
        when(service.findOrderById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/orders/99"))
                .andExpect(status().isNotFound());
    }
}