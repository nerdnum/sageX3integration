package na.com.meatco.sageX3integration.entities.woermann;

import na.com.meatco.sageX3integration.annotations.InvoiceField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class WoermannInvoice {

    @InvoiceField("number")
    private String invoiceNumber;

    @InvoiceField("date")
    private LocalDate invoiceDate;

    @InvoiceField("type")
    private String invoiceType;

    @InvoiceField("reference")
    private String refInvoceNumber;

    @InvoiceField("customerReference")
    private String orderNumber;

    @InvoiceField("totalNoVat")
    private BigDecimal invoiceAmount;

    @InvoiceField(value = "totalWithVat")
    private BigDecimal taxAmount;

    @InvoiceField(value = "lines", many=true, typeOfMany = "na.com.meatco.sageX3integration.entities.woermann.WoermannInvoiceLine")
    private List<WoermannInvoiceLine> invoiceLines;


    public WoermannInvoice() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        if (invoiceType.equals("CRN"))
            this.invoiceType = "C";
        else
            this.invoiceType = "I";
    }

    public String getRefInvoceNumber() {
        return refInvoceNumber;
    }

    public void setRefInvoceNumber(String refInvoceNumber) {
        this.refInvoceNumber = refInvoceNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public List<WoermannInvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<WoermannInvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public String toString() {
        return "WoermannInvoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", invoiceType='" + invoiceType + '\'' +
                ", refInvoceNumber='" + refInvoceNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", taxAmount=" + taxAmount +
                ", invoiceLines=" + invoiceLines +
                '}';
    }
}
