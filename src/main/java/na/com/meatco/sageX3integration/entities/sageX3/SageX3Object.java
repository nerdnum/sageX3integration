package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import na.com.meatco.sageX3integration.annotations.SageX3Field;
import na.com.meatco.sageX3integration.exceptions.SageX3ConversionException;
import na.com.meatco.sageX3integration.utils.ObjectToXmlConverter;
import na.com.meatco.sageX3integration.utils.TypeMatcher;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.commons.beanutils.PropertyUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "RESULT")
@FixedLengthRecord()
public class SageX3Object {


    @JacksonXmlProperty(localName = "GRP")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty()
    List<Group> header;


    @JacksonXmlProperty(localName = "TAB")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Table> detail;


    public SageX3Object() {
    }

    public List<Group> getHeader() {
        return header;
    }

    public void setHeader(List<Group> header) {
        this.header = header;
    }

    public void addGroup(Group theGroup){

        if(header == null){
            header = new ArrayList<>();
        }

        header.add(theGroup);
    }


    public List<Table> getDetail() {
        return detail;
    }

    public void setDetail(List<Table> detail) {
        this.detail = detail;
    }

    public void addDetail(Table theDetail){

        if(detail == null){
            detail = new ArrayList<>();
        }

        detail.add(theDetail);
    }


    @Override
    public String toString() {
        return "SageX3Object{" +
                "\n  header=" + header +
                "\n\n  detail=" + detail +
                '}';
    }



    public Object convertTo(Class<?> theClass)
            throws NullPointerException,
            IOException,
            ParserConfigurationException,
            SAXException, XPathExpressionException,
            SageX3ConversionException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {


        //Convert x3 object into an xml Document
        ObjectToXmlConverter converter = new ObjectToXmlConverter();
        Document xmlDocument = converter.toXmlDocument(this);


        XPath xPath = XPathFactory.newInstance().newXPath();

        // Using xpath get the objectXml element of the SageX3Object
        String expression = "/objectXml";
        Node resultNode = (Node)
                xPath.compile(expression)
                        .evaluate(xmlDocument, XPathConstants.NODE);

        // Create an instance of the object into which the
        // x2 object values need to be put
        Class<?> objectClass = Class.forName(theClass.getName());
        Object theObject = objectClass.newInstance();

//        try {

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
//            System.out.println("\nProcessing " + field.getName() + " of type " + field.getType().toString());


            if (field.isAnnotationPresent(SageX3Field.class)) {
                expression = field.getAnnotation(SageX3Field.class).xpath();
//                System.out.println("Found field xpath: " + expression);

                if (!field.getAnnotation(SageX3Field.class).many()){
//                    System.out.println("Search for one node");
                    Node x3node = (Node) xPath.compile(expression).evaluate(resultNode, XPathConstants.NODE);

                    // If a property for a required field was not found then throw an except
                    if (field.getAnnotation(SageX3Field.class).required() && x3node == null){
                            throw new SageX3ConversionException("No value found for requred field \"" + field.getName() + "\"");
                    }

                    // If a property was found set value in object
                    PropertyUtils.setSimpleProperty(theObject, field.getName(),
                            TypeMatcher.doConversion(field.getType(), x3node.getTextContent(), field.getAnnotation(SageX3Field.class).format()));

//                    System.out.println("Found data for " + field.getName() + ". Found: " + x3node.getTextContent());

                } else {
//                    System.out.println("Search for many node");
                    // Create class for sub items (typically it is InvoiceLines for an invoice
                    String subClassName = field.getAnnotation(SageX3Field.class).typeOfMany();
                    Class<?> subClass = Class.forName(subClassName);

                    // Create a list that will contain the many sub objects
                    List<Object> subClassList = new ArrayList<>();

                    String subExpression = "";

                    NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(resultNode, XPathConstants.NODESET);
                    for (int counter = 0; counter < nodeList.getLength(); counter++) {
                        Node currentNode = nodeList.item(counter);
//                        System.out.println(currentNode.getNodeName() + ": " + currentNode.getAttributes().getNamedItem("NUM"));

                        Object subObject = subClass.newInstance();

                        for (Field subField : subClass.getDeclaredFields()){
                            subExpression =  subField.getAnnotation(SageX3Field.class).xpath();
                            Node subNode = (Node) xPath.compile(subExpression).evaluate(currentNode, XPathConstants.NODE);

                            PropertyUtils.setSimpleProperty(subObject, subField.getName(),
                                    TypeMatcher.doConversion(subField.getType(), subNode.getTextContent(),  subField.getAnnotation(SageX3Field.class).format()));

                        }

                        subClassList.add(subObject);

                    }

//                    System.out.println(subClassList);
                    PropertyUtils.setNestedProperty(theObject, field.getName(), subClassList);
                }

            }
        }

        return theObject;
    }

}
