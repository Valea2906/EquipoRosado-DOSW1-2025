package com.furnistore.furnistore.billing;

public class TaxDecorator extends InvoiceDecorator {
    private double taxRate;

    public TaxDecorator(InvoiceComponent wrapped, double taxRate) {
        super(wrapped);
        this.taxRate = taxRate;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + String.format(java.util.Locale.US, "IVA (%.2f%%)\n", taxRate * 100);
    }

    @Override
    public double getTotal() {
        double base = wrapped.getTotal();
        return base + base * taxRate;
    }
}
