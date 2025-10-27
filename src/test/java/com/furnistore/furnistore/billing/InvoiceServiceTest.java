package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {

    private InvoiceService invoiceService;
    private Customer customer;
    private Order order;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        invoiceService = new InvoiceService();

        customer = new Customer(1L, "Juan Perez", "juan@example.com", "3001234567");
        order = new Order(1L, customer);

        product1 = new Product(1L, "Silla", "Muebles", "Moderno", 100.0, 10) {};
        product2 = new Product(2L, "Mesa", "Muebles", "Clásico", 200.0, 5) {};

        order.addProduct(product1, 2);
        order.addProduct(product2, 1);
    }

    @Test
    void testCreateInvoiceFromOrder() {
        BaseInvoice invoice = invoiceService.createInvoiceFromOrder(customer, order);
        assertEquals(customer.getName(), invoice.getDescription().split("\n")[0].replace("Factura para: ", ""));
        assertEquals(2, invoice.getItems().size());

        double expectedTotal = 2 * 100.0 + 1 * 200.0;
        assertEquals(expectedTotal, invoice.getTotal(), 0.001);
    }

    @Test
    void testApplyDiscount() {
        BaseInvoice invoice = invoiceService.createInvoiceFromOrder(customer, order);
        InvoiceComponent discounted = invoiceService.applyDiscount(invoice, 50.0);

        assertEquals(invoice.getTotal() - 50.0, discounted.getTotal(), 0.001);
        assertTrue(discounted.getDescription().contains("Descuento"));
    }

    @Test
    void testApplyTax() {
        BaseInvoice invoice = invoiceService.createInvoiceFromOrder(customer, order);
        InvoiceComponent taxed = invoiceService.applyTax(invoice, 0.1);

        assertEquals(invoice.getTotal() * 1.1, taxed.getTotal(), 0.001);
        assertTrue(taxed.getDescription().contains("IVA"));
    }

    @Test
    void testApplyShipping() {
        BaseInvoice invoice = invoiceService.createInvoiceFromOrder(customer, order);
        InvoiceComponent shipping = invoiceService.applyShipping(invoice, 30.0);

        assertEquals(invoice.getTotal() + 30.0, shipping.getTotal(), 0.001);
        assertTrue(shipping.getDescription().contains("Costo de envío"));
    }

    @Test
    void testCombinedDecorators() {
        BaseInvoice invoice = invoiceService.createInvoiceFromOrder(customer, order);
        InvoiceComponent decorated = invoiceService.applyDiscount(
                invoiceService.applyTax(
                        invoiceService.applyShipping(invoice, 20.0),
                        0.2
                ),
                50.0
        );

        double expectedTotal = (invoice.getTotal() + 20.0) * 1.2 - 50.0;
        assertEquals(expectedTotal, decorated.getTotal(), 0.001);

        String desc = decorated.getDescription();
        assertTrue(desc.contains("Costo de envío"));
        assertTrue(desc.contains("IVA"));
        assertTrue(desc.contains("Descuento"));
    }
}
