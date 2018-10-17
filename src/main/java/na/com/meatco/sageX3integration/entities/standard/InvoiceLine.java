package na.com.meatco.sageX3integration.entities.standard;

import na.com.meatco.sageX3integration.annotations.SageX3Field;

import java.math.BigDecimal;

public class InvoiceLine {

    @SageX3Field(xpath="FLD[@NAME='ITMREF']",  required = true)
    private String itemReference;

    @SageX3Field(xpath="FLD[@NAME='ITMDES']",  required = true)
    private String itemDescription;

    @SageX3Field(xpath="FLD[@NAME='SAU']",  required = true)
    private String salesUnit;

    @SageX3Field(xpath="FLD[@NAME='QTY']",  required = true)
    private BigDecimal quantity;

    @SageX3Field(xpath="FLD[@NAME='GROPRI']",  required = true)
    private BigDecimal unitPrice;

    public InvoiceLine() {
    }

    public InvoiceLine(String itemReference, String itemDescription, String salesUnit, BigDecimal quantity, BigDecimal unitPrice) {
        this.itemReference = itemReference;
        this.itemDescription = itemDescription;
        this.salesUnit = salesUnit;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "\n        InvoiceLine{" +
                "itemReference='" + itemReference + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", salesUnit=" + salesUnit +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
