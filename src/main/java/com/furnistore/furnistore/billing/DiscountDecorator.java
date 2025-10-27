package com.furnistore.furnistore.billing;

public class DiscountDecorator extends InvoiceDecorator {
    private double discountAmount; // absolute amount to subtract

    public DiscountDecorator(InvoiceComponent wrapped, double discountAmount) {
        super(wrapped);
        this.discountAmount = discountAmount;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + String.format(java.util.Locale.US, "Descuento (%.2f)\n", discountAmount);
    }


    @Override
    public double getTotal() {
        double base = wrapped.getTotal();
        return Math.max(0.0, base - discountAmount);
    }
}
