package com.furnistore.furnistore.billing;

public class TaxDecorator extends InvoiceDecorator {
    private double taxRate; // e.g., 0.19 for 19%

    public TaxDecorator(InvoiceComponent wrapped, double taxRate) {
        super(wrapped);
        this.taxRate = taxRate;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + String.format("IVA (%.2f%%)\n", taxRate*100);
    }

    @Override
    public double getTotal() {
        double base = wrapped.getTotal();
        return base + base * taxRate;
    }
}
