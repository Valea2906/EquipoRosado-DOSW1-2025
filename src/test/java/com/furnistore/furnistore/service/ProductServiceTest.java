package com.furnistore.furnistore.service;

import static org.junit.jupiter.api.Assertions.*;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.Test;


class ProductServiceTest {

    ProductService service = new ProductService();
    Product sofa = new Product(1L, "Sofá Clásico", "Sofá", "Clásico", 500.0, 10) {};

    @Test
    void testAgregarYBuscarProducto() {
        service.addProduct(sofa);
        assertEquals(sofa, service.findProductById(1L), "Debe encontrar el producto agregado");
        assertNull(service.findProductById(2L), "No debe encontrar un producto inexistente");
    }

    @Test
    void testObtenerTodosLosProductos() {
        service.addProduct(sofa);
        assertEquals(1, service.getAllProducts().size(), "La lista de productos debe contener 1 producto");
    }
}
