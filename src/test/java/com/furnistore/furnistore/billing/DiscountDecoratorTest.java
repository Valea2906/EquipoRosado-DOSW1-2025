package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDecoratorTest {

    private BaseInvoice baseInvoice;
    private Product product1;

    @BeforeEach
    void setUp() {
        baseInvoice = new BaseInvoice("Juan Perez", "juan@example.com");
        product1 = new Product(1L, "Silla", "Muebles", "Moderno", 100.0, 10) {};
        baseInvoice.addItem(new InvoiceItem(product1, 2)); // Total 200
    }

    @Test
    void testDiscountAppliedCorrectly() {
        DiscountDecorator discountInvoice = new DiscountDecorator(baseInvoice, 50.0);
        assertEquals(150.0, discountInvoice.getTotal(), 0.001);

        String desc = discountInvoice.getDescription();
        assertTrue(desc.contains("Descuento (50.00)"));
    }

    @Test
    void testDiscountCannotBeNegative() {
        DiscountDecorator discountInvoice = new DiscountDecorator(baseInvoice, 500.0);
        assertEquals(0.0, discountInvoice.getTotal(), 0.001);
    }

    @Test
    void testDiscountZero() {
        DiscountDecorator discountInvoice = new DiscountDecorator(baseInvoice, 0.0);
        assertEquals(200.0, discountInvoice.getTotal(), 0.001);
    }
}
