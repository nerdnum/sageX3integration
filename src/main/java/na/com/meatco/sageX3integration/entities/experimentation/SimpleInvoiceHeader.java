package na.com.meatco.sageX3integration.entities.experimentation;

import na.com.meatco.sageX3integration.annotations.SageX3Field;

import java.time.LocalDate;

public class SimpleInvoiceHeader {

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='ZSALFCY']")
    private String type;

    @SageX3Field(xpath="GRP[@ID='SIH1_1']/FLD[@NAME='BPCNAM']", many=true, required = true)
    private String customer;

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='INVDAT']", format="yyyyMMdd", required = true)
    private LocalDate date;

    public SimpleInvoiceHeader() {

    }


    public SimpleInvoiceHeader(String type, String customer) {
        this.type = type;
        this.customer = customer;
    }


//    public SimpleInvoiceHeader(String type, String customer, LocalDate date) {
//        this.type = type;
//        this.customer = customer;
//        this.date = date;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SimpleInvoiceHeader{" +
                "type='" + type + '\'' +
                ", customer='" + customer + '\'' +
//                ", date=" + date +
                '}';
    }
}
