package com.furnistore.furnistore.service;


import com.furnistore.furnistore.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }


    public List<Product> getAllProducts() {
        return products;
    }


    public Product findProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
