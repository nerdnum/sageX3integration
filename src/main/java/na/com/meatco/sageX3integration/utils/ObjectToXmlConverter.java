package na.com.meatco.sageX3integration.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ObjectToXmlConverter {

    private XmlMapper xmlMapper;

    public ObjectToXmlConverter() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    public Document toXmlDocument(Object object) throws IOException, ParserConfigurationException, SAXException {

        String xmlString = xmlMapper.writeValueAsString(object);
        InputStream xmlStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(xmlStream);

        return xmlDocument;
    }

    public String toXmlString(Object object) throws JsonProcessingException {
        String xmlString = xmlMapper.writeValueAsString(object);
        return xmlString;
    }

}
