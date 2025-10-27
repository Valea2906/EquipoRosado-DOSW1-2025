package com.furnistore.furnistore.billing;

public class ShippingDecorator extends InvoiceDecorator {
    private double shippingCost;

    public ShippingDecorator(InvoiceComponent wrapped, double shippingCost) {
        super(wrapped);
        this.shippingCost = shippingCost;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + String.format(java.util.Locale.US, "Costo de envío (%.2f)\n", shippingCost);
    }


    @Override
    public double getTotal() {
        return wrapped.getTotal() + shippingCost;
    }
}
