package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceDecoratorTest {

    private BaseInvoice baseInvoice;
    private InvoiceDecorator decorator;

    @BeforeEach
    void setUp() {
        baseInvoice = new BaseInvoice("Juan Perez", "juan@example.com");
        Product product1 = new Product(1L, "Mesa", "Muebles", "Moderno", 200.0, 5) {};
        baseInvoice.addItem(new InvoiceItem(product1, 1));

        decorator = new InvoiceDecorator(baseInvoice) {};
    }

    @Test
    void testDelegatesTotal() {
        assertEquals(200.0, decorator.getTotal(), 0.001);
    }

    @Test
    void testDelegatesDescription() {
        String desc = decorator.getDescription();
        assertTrue(desc.contains("Factura para: Juan Perez"));
        assertTrue(desc.contains("Mesa x1 -> 200.00"));
    }
}
