package com.furnistore.furnistore.controller;


import com.furnistore.furnistore.model.Product;
import com.furnistore.furnistore.service.ProductService;

public class ProductController {

    private ProductService productService = new ProductService();

    public void addProduct(Product product) {
        productService.addProduct(product);
    }


    public void listProducts() {
        productService.getAllProducts().forEach(p ->
                System.out.println("Product: " + p.getName() + ", Price: " + p.getPrice() + ", Stock: " + p.getStockQuantity())
        );
    }
}
