package com.furnistore.furnistore.model;


public class Inventory {

    private Long productId;
    private int quantity;

    public Inventory(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public void updateStock(int amount) {
        this.quantity += amount;
    }


    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
