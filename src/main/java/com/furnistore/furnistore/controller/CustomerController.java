package com.furnistore.furnistore.controller;


import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.service.CustomerService;

public class CustomerController {

    private CustomerService customerService = new CustomerService();

    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }


    public void listCustomers() {
        customerService.getAllCustomers().forEach(c ->
                System.out.println("Customer: " + c.getName() + ", Email: " + c.getEmail())
        );
    }
}
