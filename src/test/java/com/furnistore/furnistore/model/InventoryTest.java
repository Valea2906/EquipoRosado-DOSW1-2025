package com.furnistore.furnistore.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(1L, 10);
    }

    @Test
    void testUpdateStockIncrease() {
        inventory.updateStock(5);
        assertEquals(15, inventory.getQuantity(), "El stock debe incrementarse correctamente");
    }

    @Test
    void testUpdateStockDecrease() {
        inventory.updateStock(-3);
        assertEquals(7, inventory.getQuantity(), "El stock debe disminuir correctamente");
    }

    @Test
    void testUpdateStockToNegative() {

        inventory.updateStock(-20);
        assertEquals(-10, inventory.getQuantity(), "El stock puede ser negativo si se resta demasiado");
    }

    @Test
    void testGettersAndSetters() {
        inventory.setProductId(2L);
        inventory.setQuantity(50);
        assertEquals(2L, inventory.getProductId(), "El productId debe actualizarse correctamente");
        assertEquals(50, inventory.getQuantity(), "La cantidad debe actualizarse correctamente");
    }

    @Test
    void testUpdateStockMultipleCalls() {

        inventory.updateStock(10);
        inventory.updateStock(-5);
        inventory.updateStock(0);
        assertEquals(15, inventory.getQuantity(), "El stock debe reflejar todas las actualizaciones consecutivas");
    }
}

