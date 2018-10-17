package na.com.meatco.sageX3integration.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class XmlPrettyPrint {
    /*

    This class receives xml data in the following formats:
        javax.xml.transform.dom.DOMSource
        org.w3c.dom.Document
        java.lang.String

    It then transforms the data into "pretty" indented human readable xml data
    and returns the "pretty" xml data as java.lang.String}

     */

    public XmlPrettyPrint() {
    }

    private String transformer(DOMSource source, int indent) throws TransformerException {

        // Setup pretty print options
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", indent);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Return pretty print xml string
        StringWriter stringWriter = new StringWriter();
        transformer.transform(source, new StreamResult(stringWriter));
        return stringWriter.toString();

    }

    public String toPrettyString(DOMSource source, int indent) {
        try {

            return transformer(source, indent);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toPrettyString(Document document, int indent) {
        try {
            // Remove whitespaces outside tags
            document.normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                    document,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            return transformer(new DOMSource(document), 4);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toPrettyString(String xml, int indent) {
        try {

            // Turn String into a document import org.w3c.dom.Document;
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))));

            return toPrettyString(document, indent);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
