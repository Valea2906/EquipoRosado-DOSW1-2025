package com.furnistore.furnistore.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class SupplierTest {

    Product sofa = new Product(1L, "Sofá Clásico", "Sofá", "Clásico", 500.0, 10) {};
    Supplier supplier = new Supplier(1L, "Muebles S.A.", "contacto@muelles.com");

    @Test
    void testEnviarProducto() {
        supplier.sendProduct(sofa, 5);
        assertEquals(15, sofa.getStockQuantity(), "El stock del producto debe incrementarse correctamente al enviarlo el proveedor");
    }
}
