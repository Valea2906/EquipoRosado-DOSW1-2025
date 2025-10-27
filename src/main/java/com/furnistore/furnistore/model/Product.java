package com.furnistore.furnistore.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FurnitureProduct.class, name = "furniture")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String style;
    private Double price;
    private int stockQuantity;

    public Product() {}

    public Product(Long id, String name, String category, String style, Double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.style = style;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public void decreaseStock(int amount) {
        if (amount <= stockQuantity) stockQuantity -= amount;
        else throw new IllegalArgumentException("No hay suficiente stock del producto: " + name);
    }

    public void increaseStock(int amount) {
        stockQuantity += amount;
    }
}
