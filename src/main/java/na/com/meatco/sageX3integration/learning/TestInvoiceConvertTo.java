package na.com.meatco.sageX3integration.learning;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.standard.Invoice;
import na.com.meatco.sageX3integration.entities.sageX3.SageX3Object;
import na.com.meatco.sageX3integration.entities.woermann.WoermannInvoice;
import na.com.meatco.sageX3integration.exceptions.SageX3ConversionException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestInvoiceConvertTo {

    public static void main(String[] args)
            throws XMLStreamException,
            IOException,
            ParserConfigurationException,
            SAXException, XPathExpressionException, SageX3ConversionException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        //Read X3 xml and convert to x3 object
        FileInputStream fis = new FileInputStream("src/main/resources/templates/invoice.xml");
        XMLInputFactory f = XMLInputFactory.newFactory();
        XMLStreamReader sr = f.createXMLStreamReader(fis);
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        sr.next(); // to point to <root>
        sr.next(); // to point to root-element under root
        SageX3Object x3 = mapper.readValue(sr, SageX3Object.class);
        sr.close();

        Invoice sih = (Invoice) x3.convertTo(Invoice.class);

        System.out.println("======================== Standard Invoice =========================");

        System.out.println(sih);

        System.out.println("======================== Woerman Invoice =========================");

        WoermannInvoice wInvoice = (WoermannInvoice) sih.convertTo(WoermannInvoice.class);

        System.out.println(wInvoice);

    }

}
