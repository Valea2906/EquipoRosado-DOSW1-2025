package com.furnistore.furnistore.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ProductTest {

    Product sofa = new Product(1L, "Sofá Clásico", "Sofá", "Clásico", 500.0, 10) {};

    @Test
    void testDecreaseStock() {
        sofa.decreaseStock(3);
        assertEquals(7, sofa.getStockQuantity(), "La cantidad en stock debería disminuir correctamente");
    }

    @Test
    void testDecreaseStockInsuficiente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sofa.decreaseStock(20));
        assertEquals("No hay suficiente stock del producto: Sofá Clásico",
                exception.getMessage(),
                "Debe lanzar excepción cuando el stock es insuficiente");
    }


    @Test
    void testIncreaseStock() {
        sofa.increaseStock(5);
        assertEquals(15, sofa.getStockQuantity(), "La cantidad en stock debería aumentar correctamente");
    }
}
