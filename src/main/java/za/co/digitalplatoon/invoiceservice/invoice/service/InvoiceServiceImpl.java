package za.co.digitalplatoon.invoiceservice.invoice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoiceservice.invoice.dao.InvoiceDao;
import za.co.digitalplatoon.invoiceservice.invoice.model.Invoice;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private InvoiceDao dao;

    @Override
    public List<Invoice> getAllInvoices() {
        return dao.getAllInvoices();
    }

    @Override
    public Invoice getInvoiceById(Long id) {

        return dao.getInvoiceById(id);
    }

    @Override
    public void save(Invoice invoice) {
        dao.save(invoice);

    }
}
