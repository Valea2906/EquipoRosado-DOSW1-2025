package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST /clientes → registro de clientes
    @PostMapping("/clientes")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.addCustomer(customer);
        return ResponseEntity.ok(saved);
    }

    // GET /clientes → listar todos los clientes
    @GetMapping("/clientes")
    public ResponseEntity<List<Customer>> listCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // GET /clientes/{id} → buscar cliente por id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Long id) {
        Customer found = customerService.findCustomerById(id);
        if (found == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(found);
    }
}
