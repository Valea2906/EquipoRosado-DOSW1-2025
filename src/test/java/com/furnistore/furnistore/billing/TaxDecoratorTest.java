package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxDecoratorTest {

    @Test
    void testTaxCalculationAndDescription() {
        Product chair = new Product(1L, "Silla", "Muebles", "Moderno", 100.0, 10) {};
        BaseInvoice invoice = new BaseInvoice("Juan", "juan@example.com");
        invoice.addItem(new InvoiceItem(chair, 2)); // total 200

        InvoiceComponent taxedInvoice = new TaxDecorator(invoice, 0.19);

        assertEquals(238.0, taxedInvoice.getTotal(), 0.001);

        String desc = taxedInvoice.getDescription();
        assertTrue(desc.contains("Factura para: Juan"));
        assertTrue(desc.contains("Silla x2 -> 200.00"));
        assertTrue(desc.contains("IVA (19.00%)"));
    }
}
