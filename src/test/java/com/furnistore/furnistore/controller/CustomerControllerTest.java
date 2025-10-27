package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.service.CustomerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCustomer() throws Exception {
        Customer c = new Customer(null, "Juan Torres", "juan@example.com", "3001112233");

        when(service.addCustomer(any(Customer.class))).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            customer.setId(1L);
            return customer;
        });

        mockMvc.perform(post("/api/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Juan Torres"));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        when(service.getAllCustomers()).thenReturn(List.of(
                new Customer(1L, "Juan Torres", "juan@example.com", "3001112233")
        ));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Juan Torres"));
    }

    @Test
    void testGetCustomerByIdFound() throws Exception {
        when(service.findCustomerById(1L))
                .thenReturn(Optional.of(new Customer(1L, "Juan Torres", "juan@example.com", "3001112233")));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan Torres"));
    }

    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        when(service.findCustomerById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/customers/99"))
                .andExpect(status().isNotFound());
    }
}
