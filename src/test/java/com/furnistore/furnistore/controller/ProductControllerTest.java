package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.Test;

class ProductControllerTest {

    ProductController controller = new ProductController();
    Product sofa = new Product(1L, "Sofá Clásico", "Sofá", "Clásico", 500.0, 10) {};

    @Test
    void testAgregarYListarProducto() {
        controller.addProduct(sofa);
    }

}