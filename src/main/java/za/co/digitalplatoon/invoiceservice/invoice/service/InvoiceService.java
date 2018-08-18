package za.co.digitalplatoon.invoiceservice.invoice.service;

import za.co.digitalplatoon.invoiceservice.invoice.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    void save(Invoice invoice);
}
