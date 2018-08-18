package za.co.digitalplatoon.invoiceservice.invoice.dao;

import za.co.digitalplatoon.invoiceservice.invoice.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    void save(Invoice invoice);
}
