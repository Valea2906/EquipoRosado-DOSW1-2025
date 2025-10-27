package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.model.OrderItem;
import com.furnistore.furnistore.model.Product;

public class InvoiceService {

    /**
     * Crea una factura base a partir de un pedido
     */
    public BaseInvoice createInvoiceFromOrder(Customer customer, Order order) {
        BaseInvoice invoice = new BaseInvoice(customer.getName(), customer.getEmail());

        for (OrderItem oi : order.getItems()) {
            invoice.addItem(new InvoiceItem(oi.getProduct(), oi.getQuantity()));
        }

        return invoice;
    }

    /**
     * Aplica un descuento a la factura usando el decorador
     */
    public InvoiceComponent applyDiscount(InvoiceComponent invoice, double discountAmount) {
        return new DiscountDecorator(invoice, discountAmount);
    }

    /**
     * Aplica el IVA a la factura usando el decorador
     */
    public InvoiceComponent applyTax(InvoiceComponent invoice, double taxRate) {
        return new TaxDecorator(invoice, taxRate);
    }

    /**
     * Aplica el costo de envío a la factura usando el decorador
     */
    public InvoiceComponent applyShipping(InvoiceComponent invoice, double shippingCost) {
        return new ShippingDecorator(invoice, shippingCost);
    }
}
