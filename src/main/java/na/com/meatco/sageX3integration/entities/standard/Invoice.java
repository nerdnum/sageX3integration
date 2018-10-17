package na.com.meatco.sageX3integration.entities.standard;

import na.com.meatco.sageX3integration.annotations.InvoiceField;
import na.com.meatco.sageX3integration.annotations.SageX3Field;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Invoice {

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='SIVTYP']", required = true)
    private String type;

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='NUM']", required = true)
    private String number;

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='INVDAT']", format="yyyyMMdd", required = true)
    private LocalDate date;

    @SageX3Field(xpath="GRP[@ID='SIH1_1']/FLD[@NAME='BPCORD']", required = true)
    private String customerReference;

    @SageX3Field(xpath="GRP[@ID='SIH1_1']/FLD[@NAME='BPCNAM']", required = true)
    private String customerName;

    @SageX3Field(xpath="GRP[@ID='SIH0_1']/FLD[@NAME='INVREF']", required = false)
    private String reference;

    @SageX3Field(xpath="GRP[@ID='SIH2_1']/FLD[@NAME='CUR']", required = true)
    private String currency;

    @SageX3Field(xpath="GRP[@ID='SIH2_1']/FLD[@NAME='RAT2']", format = "#.##", required = true)
    private BigDecimal exchangeRate;

    @SageX3Field(xpath="GRP[@ID='SIH2_2']/FLD[@NAME='STRDUDDAT']", format="yyyyMMdd", required = true)
    private LocalDate dueDate;

    @SageX3Field(xpath="GRP[@ID='SIH2_2']/FLD[@NAME='ZPTE']", required = true)
    private String paymentTerms;

    @SageX3Field(xpath="GRP[@ID='SIH4_3']/FLD[@NAME='INVNOT']", format = "#.##", required = true)
    private BigDecimal totalNoVat;

    @SageX3Field(xpath="GRP[@ID='SIH4_3']/FLD[@NAME='INVATI']", format = "#.##", required = true)
    private BigDecimal totalWithVat;

    @SageX3Field(xpath="TAB[@ID='SIH4_1']/LIN", required = true, many=true, typeOfMany = "na.com.meatco.sageX3integration.entities.standard.InvoiceLine")
    private List<InvoiceLine> lines;

    @Override
    public String toString() {
        return "Invoice{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", customerReference='" + customerReference + '\'' +
                ", customerName='" + customerName + '\'' +
                ", reference='" + reference + '\'' +
                ", currency='" + currency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", dueDate=" + dueDate +
                ", paymentTerms='" + paymentTerms + '\'' +
                ", totalNoVat=" + totalNoVat +
                ", totalWithVat=" + totalWithVat +
                ", lines=" + lines +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public BigDecimal getTotalNoVat() {
        return totalNoVat;
    }

    public void setTotalNoVat(BigDecimal totalNoVat) {
        this.totalNoVat = totalNoVat;
    }

    public BigDecimal getTotalWithVat() {
        return totalWithVat;
    }

    public void setTotalWithVat(BigDecimal totalWithVat) {
        this.totalWithVat = totalWithVat;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    public Object convertTo(Class<?> theClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        System.out.println("Converting invoice....");

        Class<?> objectClass = Class.forName(theClass.getName());
        Object theObject = objectClass.newInstance();
        String requiredFieldName = "";

        for (Field field: objectClass.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(InvoiceField.class)){
                if (!field.getAnnotation(InvoiceField.class).many()) {
                    System.out.println("Get value for " + field.getName());

                    requiredFieldName = field.getAnnotation(InvoiceField.class).value();
                    Object requiredFieldObject = PropertyUtils.getSimpleProperty(this, requiredFieldName);

                    System.out.println("Got value: " + requiredFieldObject);

                    System.out.println("Set value for " + field.getName());
                    PropertyUtils.setSimpleProperty(theObject, field.getName(), requiredFieldObject);
                } else {

                    String subClassName = field.getAnnotation(InvoiceField.class).typeOfMany();
                    Class<?> subClass = Class.forName(subClassName);

                    // Create a list that will contain the many sub objects

                    System.out.println("====================== Get value for many field " + field.getName());

                    requiredFieldName = field.getAnnotation(InvoiceField.class).value();
                    List<Object> subClassList = (List<Object>) PropertyUtils.getNestedProperty(this, requiredFieldName);
                    Iterator<Object> objectIter = subClassList.iterator();

                    List<Object> targetObjectList = new ArrayList<>();
                    String requiredSubFieldName = "";

                    while(objectIter.hasNext()){

                        Object object = objectIter.next();

                        Object subObject = subClass.newInstance();

                        for (Field subField : subClass.getDeclaredFields()){

                            if(subField.isAnnotationPresent(InvoiceField.class)) {

                                requiredSubFieldName = subField.getAnnotation(InvoiceField.class).value();
                                Object requiredFieldObject = PropertyUtils.getSimpleProperty(object, requiredSubFieldName);
                                PropertyUtils.setSimpleProperty(subObject, subField.getName(), requiredFieldObject);

                            }
                        }
                        targetObjectList.add(subObject);
                    }


                    PropertyUtils.setNestedProperty(theObject, field.getName(), targetObjectList);

                }
            }
        }

        return theObject;

    }


}
