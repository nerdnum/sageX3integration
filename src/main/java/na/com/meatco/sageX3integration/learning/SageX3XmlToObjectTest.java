package na.com.meatco.sageX3integration.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.sageX3.SageX3Object;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class SageX3XmlToObjectTest {


    public static void main(String[] args) throws IOException, XMLStreamException {

        FileInputStream fis = new FileInputStream("data/output/xml/finalInvoice.xml");

        XMLInputFactory f = XMLInputFactory.newFactory();

        XMLStreamReader sr = f.createXMLStreamReader(fis);

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        sr.next(); // to point to <root>
        sr.next(); // to point to root-element under root

        SageX3Object x3 = mapper.readValue(sr, SageX3Object.class);

        sr.close();

        System.out.println(x3);

        System.out.println("\n=================================== XML =====================================\n");

//        SageX3Object newX3 = x3;
//
//        System.out.println(mapper.writeValueAsString(newX3));

        System.out.println("\n=================================== JSON =====================================\n");

        ObjectMapper jsonMapper = new ObjectMapper();
        System.out.println(jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(x3));


    }


}
