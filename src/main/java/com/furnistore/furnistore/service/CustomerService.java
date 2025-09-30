package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }


    public List<Customer> getAllCustomers() {
        return customers;
    }


    public Customer findCustomerById(Long id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
