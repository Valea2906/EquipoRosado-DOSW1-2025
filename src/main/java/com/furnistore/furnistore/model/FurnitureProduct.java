package com.furnistore.furnistore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "furniture_products")
public class FurnitureProduct extends Product {
    public FurnitureProduct() {}
    public FurnitureProduct(Long id, String name, String category, String style, Double price, int stockQuantity) {
        super(id, name, category, style, price, stockQuantity);
    }
}
