package za.co.digitalplatoon.invoiceservice.invoice.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    private String client;

    @NotNull
    private Long vatRate;

    private LocalDateTime invoiceDate = LocalDateTime.now();

    @OneToMany(targetEntity = LineItem.class, mappedBy = "invoice", fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<LineItem> lineItems = new HashSet<>();

    public Invoice() {
    }

    public Long getID() {
        return ID;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    //@OneToMany(targetEntity = LineItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {

        this.lineItems.addAll(lineItems);
    }

    public BigDecimal getSubtotal(){
        BigDecimal invoiveTotal = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        for (LineItem lineItem: getLineItems()) {
            invoiveTotal.add(lineItem.getLineItemTotal());
        }
        return invoiveTotal;
    }

    public BigDecimal getVat(){
        BigDecimal vatTotal = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        for (LineItem lineItem: getLineItems()) {
            vatTotal.add(lineItem.getLineItemTotal().multiply(new BigDecimal(vatRate.toString())));
        }
        return vatTotal.divide(new BigDecimal(100).setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal getTotal(){
        return getSubtotal().add(getVat()).setScale(2,BigDecimal.ROUND_HALF_UP);
    }


}
