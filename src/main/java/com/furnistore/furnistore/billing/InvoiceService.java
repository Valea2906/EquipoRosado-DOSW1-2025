package com.furnistore.furnistore.billing;

import com.furnistore.furnistore.model.Customer;
import com.furnistore.furnistore.model.Order;
import com.furnistore.furnistore.model.Product;

public class InvoiceService {

    public BaseInvoice createInvoiceFromOrder(Customer customer, Order order) {
        BaseInvoice invoice = new BaseInvoice(customer.getName(), customer.getEmail());
        order.getItems().forEach(oi -> {
            Product p = oi.getProduct();
            invoice.addItem(new InvoiceItem(p, oi.getQuantity()));
        });
        return invoice;
    }

    public InvoiceComponent applyTax(InvoiceComponent invoice, double taxRate) {
        return new TaxDecorator(invoice, taxRate);
    }

    public InvoiceComponent applyDiscount(InvoiceComponent invoice, double amount) {
        return new DiscountDecorator(invoice, amount);
    }

    public InvoiceComponent applyShipping(InvoiceComponent invoice, double shippingCost) {
        return new ShippingDecorator(invoice, shippingCost);
    }
}
