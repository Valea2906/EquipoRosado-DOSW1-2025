package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    CustomerService service = new CustomerService();
    Customer customer = new Customer(1L, "Ana", "ana@email.com", "3001234567");

    @Test
    void testAgregarYBuscarCliente() {
        service.addCustomer(customer);
        assertEquals(customer, service.findCustomerById(1L), "Debe encontrar el cliente agregado");
        assertNull(service.findCustomerById(2L), "No debe encontrar un cliente inexistente");
    }

    @Test
    void testObtenerTodosLosClientes() {
        service.addCustomer(customer);
        assertEquals(1, service.getAllCustomers().size(), "La lista de clientes debe contener 1 cliente");
    }
}