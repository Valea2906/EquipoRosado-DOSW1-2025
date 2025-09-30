package com.furnistore.furnistore.model;

public class Supplier {
    private Long id;
    private String name;
    private String contact;

    public Supplier(Long id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public void sendProduct(Product product, int quantity) {
        product.increaseStock(quantity);
        System.out.println(quantity + " units of " + product.getName() + " sent by supplier " + name);
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }
}
