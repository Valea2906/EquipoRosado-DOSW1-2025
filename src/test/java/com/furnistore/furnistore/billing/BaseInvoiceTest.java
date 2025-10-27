package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseInvoiceTest {

    private BaseInvoice invoice;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        invoice = new BaseInvoice("Juan Perez", "juan@example.com");

        product1 = new Product(1L, "Silla", "Muebles", "Moderno", 100.0, 10) {};
        product2 = new Product(2L, "Mesa", "Muebles", "Clásico", 200.0, 5) {};
    }

    @Test
    void testAddItemAndTotal() {
        invoice.addItem(new InvoiceItem(product1, 2));
        invoice.addItem(new InvoiceItem(product2, 1));

        assertEquals(2, invoice.getItems().size());
        assertEquals(400.0, invoice.getTotal(), 0.001);
    }

    @Test
    void testDescriptionContainsItems() {
        invoice.addItem(new InvoiceItem(product1, 2));
        invoice.addItem(new InvoiceItem(product2, 1));

        String desc = invoice.getDescription();
        assertTrue(desc.contains("Factura para: Juan Perez"));
        assertTrue(desc.contains("Silla x2 -> 200.00"));
        assertTrue(desc.contains("Mesa x1 -> 200.00"));
    }

    @Test
    void testCustomerEmail() {
        assertEquals("juan@example.com", invoice.getCustomerEmail());
    }

    @Test
    void testEmptyInvoice() {
        assertEquals(0, invoice.getItems().size());
        assertEquals(0.0, invoice.getTotal(), 0.001);
        assertTrue(invoice.getDescription().contains("Factura para: Juan Perez"));
    }
}
