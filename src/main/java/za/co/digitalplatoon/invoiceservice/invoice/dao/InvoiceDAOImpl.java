package za.co.digitalplatoon.invoiceservice.invoice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import za.co.digitalplatoon.invoiceservice.invoice.model.Invoice;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class InvoiceDAOImpl implements InvoiceDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Invoice> getAllInvoices() {
        //Open a session
        Session session = sessionFactory.openSession();

        //Retrieve a list of all invoices
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> invoiceRoot = criteriaQuery.from(Invoice.class);
        criteriaQuery.select(invoiceRoot);
        Query invoiceQuery = session.createQuery(criteriaQuery);
        List<Invoice> invoiceList = invoiceQuery.setMaxResults(10).getResultList();

        //Close the session
        session.close();
        return invoiceList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Invoice getInvoiceById(Long id) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Retrieve object from the database if it exists
        //TODO refactor code to work more efficiently
        List<Invoice> invoices = getAllInvoices();
        Invoice newInvoice = null;
        for (Invoice invoice: invoices
             ) {if(invoice.getID().equals(id)) newInvoice = invoice;
        }

        //Close the session
        session.close();

        return newInvoice;
    }

    @Override
    public void save(Invoice invoice) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a Transaction
        session.getTransaction();

        //Save Entity to Database
        session.save(invoice);

        //Commit the Transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
    }
}
