package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceItemTest {

    @Test
    void testInvoiceItem() {
        Product product = new Product(1L, "Silla", "Muebles", "Moderno", 150.0, 10) {};
        InvoiceItem item = new InvoiceItem(product, 3);

        assertEquals(product, item.getProduct());
        assertEquals(3, item.getQuantity());
        assertEquals("Silla x3", item.getDescription());
        assertEquals(450.0, item.getTotal(), 0.001);
    }
}

