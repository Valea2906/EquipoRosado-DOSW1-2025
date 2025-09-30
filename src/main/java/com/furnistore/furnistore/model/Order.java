package com.furnistore.furnistore.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private LocalDate date;
    private String status;
    private Double total;
    private Customer customer;
    private List<Product> products;

    public Order(Long id, Customer customer) {
        this.id = id;
        this.date = LocalDate.now();
        this.status = "Pending";
        this.total = 0.0;
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        product.decreaseStock(quantity);
        for (int i=0;i<quantity;i++) products.add(product);
        calculateTotal();
    }

    public void calculateTotal() {
        total = products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void updateStatus(String newStatus) { this.status = newStatus; }


    public Long getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public Double getTotal() {
        return total;
    }
    public Customer getCustomer() {
        return customer;
    }
    public List<Product> getProducts() {
        return products;
    }
}
