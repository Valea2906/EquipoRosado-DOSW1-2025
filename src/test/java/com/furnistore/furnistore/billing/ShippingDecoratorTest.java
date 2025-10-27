package com.furnistore.furnistore.billing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingDecoratorTest {

    @Test
    void testTotalIncludesShipping() {
        BaseInvoice baseInvoice = new BaseInvoice("Juan Perez", "juan@example.com");
        baseInvoice.addItem(new InvoiceItem(new TestProduct("Silla", 100.0), 2)); // total 200

        ShippingDecorator shippingInvoice = new ShippingDecorator(baseInvoice, 50.0);
        assertEquals(250.0, shippingInvoice.getTotal(), 0.001);
    }

    @Test
    void testDescriptionIncludesShipping() {
        BaseInvoice baseInvoice = new BaseInvoice("Juan Perez", "juan@example.com");
        baseInvoice.addItem(new InvoiceItem(new TestProduct("Mesa", 150.0), 1)); // total 150

        ShippingDecorator shippingInvoice = new ShippingDecorator(baseInvoice, 25.0);

        String description = shippingInvoice.getDescription();
        assertTrue(description.contains("Mesa x1 -> 150.00"));
        assertTrue(description.contains("Costo de envío (25.00)"));
    }

    // Clase auxiliar para pruebas
    static class TestProduct extends com.furnistore.furnistore.model.Product {
        public TestProduct(String name, double price) {
            super(null, name, "Categoria", "Estilo", price, 10);
        }
    }
}
