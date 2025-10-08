package com.furnistore.furnistore.billing;

public abstract class InvoiceDecorator implements InvoiceComponent {
    protected InvoiceComponent wrapped;

    public InvoiceDecorator(InvoiceComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public double getTotal() {
        return wrapped.getTotal();
    }
}
