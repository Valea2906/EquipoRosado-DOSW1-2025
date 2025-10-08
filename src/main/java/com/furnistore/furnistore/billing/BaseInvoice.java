package com.furnistore.furnistore.billing;

import java.util.ArrayList;
import java.util.List;

public class BaseInvoice implements InvoiceComponent {
    private String customerName;
    private String customerEmail;
    private List<InvoiceItem> items = new ArrayList<>();

    public BaseInvoice(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public void addItem(InvoiceItem item) {
        items.add(item);
    }

    public List<InvoiceItem> getItems() { return items; }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura para: ").append(customerName).append("\n");
        for (InvoiceItem it : items) {
            sb.append(it.getDescription()).append(" -> ").append(String.format("%.2f", it.getTotal())).append("\n");
        }
        return sb.toString();
    }

    @Override
    public double getTotal() {
        return items.stream().mapToDouble(InvoiceItem::getTotal).sum();
    }

    public String getCustomerEmail() { return customerEmail; }
}
