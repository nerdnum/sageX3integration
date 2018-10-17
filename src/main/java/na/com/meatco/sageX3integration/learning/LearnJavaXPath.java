package na.com.meatco.sageX3integration.learning;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class LearnJavaXPath {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        FileInputStream fis = new FileInputStream("src/main/resources/templates/invoice.xml");

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(fis);


        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/RESULT";

        NodeList resultNodeList = (NodeList)
                xPath.compile(expression)
                        .evaluate(xmlDocument, XPathConstants.NODESET);

        Node resultNode = resultNodeList.item(0);

        expression = "GRP[@ID='SIH0_1']/FLD[@NAME='INVDAT']";

        NodeList groupNodeList = (NodeList)
                xPath.compile(expression).evaluate(resultNode, XPathConstants.NODESET);



        System.out.println("Node count:" + groupNodeList.getLength());


        resultNode = groupNodeList.item(0);
        System.out.println("The value for " + resultNode.getAttributes().getNamedItem("NAME").getTextContent() +
                " is \"" + resultNode.getTextContent() + "\"" );
        resultNode.getTextContent();


    }

}
