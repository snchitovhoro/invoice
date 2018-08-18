package za.co.digitalplatoon.invoiceservice.invoice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //@NotNull
    private Long quantity;

   // @NotNull
    private String description;

    //@NotNull
    private BigDecimal unitPrice;

    @ManyToOne
    private Invoice invoice;

    public LineItem() {
    }

    public Long getId() {
        return Id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getLineItemTotal(){
        
        return unitPrice.multiply(new BigDecimal(quantity.toString()).setScale(2,BigDecimal.ROUND_HALF_UP));        
    }

}
