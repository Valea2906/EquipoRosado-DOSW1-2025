package com.furnistore.furnistore.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String status;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public Order(Long id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.date = LocalDate.now();
        this.status = "Pending";
        this.total = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        product.decreaseStock(quantity);
        items.add(new OrderItem(product, quantity));
        calculateTotal();
    }

    public void calculateTotal() {
        total = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public Double getTotal() { return total; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
}
