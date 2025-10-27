package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();
    private Long nextId = 1L;

    // Crear nuevo cliente
    public Customer addCustomer(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(nextId++);
        }
        customers.add(customer);
        return customer;
    }

    // Listar todos los clientes
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Buscar cliente por ID
    public Customer findCustomerById(Long id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Eliminar cliente por ID (extra útil para API)
    public boolean deleteCustomer(Long id) {
        return customers.removeIf(c -> c.getId().equals(id));
    }
}
