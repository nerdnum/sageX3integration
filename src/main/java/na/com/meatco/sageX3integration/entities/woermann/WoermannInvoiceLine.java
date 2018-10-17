package na.com.meatco.sageX3integration.entities.woermann;

import na.com.meatco.sageX3integration.annotations.InvoiceField;

import java.math.BigDecimal;

public class WoermannInvoiceLine {

    private static final String recordType = "3";

    private String ourItemNumber;

    @InvoiceField("itemReference")
    private String yourItemNumber;

    @InvoiceField("quantity")
    private BigDecimal lineQuantity;

    @InvoiceField("unitPrice")
    private BigDecimal lineAmount;

    @InvoiceField("unitPrice")
    private BigDecimal taxAmount;

    @InvoiceField("unitPrice")
    private BigDecimal itemWeight;

    @InvoiceField("unitPrice")
    private BigDecimal transportAmount;

    public static String getRecordType() {
        return recordType;
    }

    public String getOurItemNumber() {
        return ourItemNumber;
    }

    public void setOurItemNumber(String ourItemNumber) {
        this.ourItemNumber = ourItemNumber;
    }

    public String getYourItemNumber() {
        return yourItemNumber;
    }

    public void setYourItemNumber(String yourItemNumber) {
        this.yourItemNumber = yourItemNumber;
    }

    public BigDecimal getLineQuantity() {
        return lineQuantity;
    }

    public void setLineQuantity(BigDecimal lineQuantity) {
        this.lineQuantity = lineQuantity;
    }

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(BigDecimal itemWeight) {
        this.itemWeight = itemWeight;
    }

    public BigDecimal getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(BigDecimal transportAmount) {
        this.transportAmount = transportAmount;
    }

    @Override
    public String toString() {
        return "WoermannInvoiceLine{" +
                "recordType='" + recordType + '\'' +
                "  ourItemNumber='" + ourItemNumber + '\'' +
                ", yourItemNumber='" + yourItemNumber + '\'' +
                ", lineQuantity=" + lineQuantity +
                ", lineAmount=" + lineAmount +
                ", taxAmount=" + taxAmount +
                ", itemWeight=" + itemWeight +
                ", transportAmount=" + transportAmount +
                '}';
    }
}
