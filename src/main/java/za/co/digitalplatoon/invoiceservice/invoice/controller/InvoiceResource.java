package za.co.digitalplatoon.invoiceservice.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoiceservice.invoice.model.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.service.InvoiceService;

import java.util.List;

@RestController
public class InvoiceResource {
    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public void addInvoice(@RequestBody Invoice invoice){
        invoiceService.save(invoice);
    }

    @RequestMapping(value = "/invoices/{invoiceId}", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable Long invoiceId){
        return invoiceService.getInvoiceById(invoiceId);
        }

}
