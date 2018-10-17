package na.com.meatco.sageX3integration.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;


@Component
@Slf4j
public class SageX3SoapMessageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        log.info("Running SageX3SoapMessageProcessor");

        NodeList nodeList = (NodeList) exchange.getIn().getBody();

//        System.out.println("\n============================== Soap Message Processor Start ===============================\n");

        Node x3Response = nodeList.item(0).getFirstChild();

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "readReturn/status";

        Node statusNode = (Node) xPath.compile(expression).evaluate(x3Response ,XPathConstants.NODE);

        if(statusNode.getTextContent().equals("0")){
            expression = "readReturn/messages/message";
            NodeList messageNodes = (NodeList) xPath.compile(expression).evaluate(x3Response ,XPathConstants.NODESET);

            StringBuilder strBuilder = new StringBuilder();
            for(int counter = 0; counter < messageNodes.getLength(); counter++){
                if (counter == 0)
                    strBuilder.append(messageNodes.item(counter).getTextContent().trim());
                else
                    strBuilder.append("\n" + messageNodes.item(counter).getTextContent());
            }

            exchange.getIn().setBody(strBuilder.toString());
            exchange.getIn().setHeader("status", 0);

        } else {

            expression = "readReturn/resultXml";
            Node resultXmlNode = (Node) xPath.compile(expression).evaluate(x3Response ,XPathConstants.NODE);

            String resultXmlNodeContent = resultXmlNode.getTextContent();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document resultXml = builder.parse(new ByteArrayInputStream(resultXmlNodeContent.getBytes("UTF-8")));

            exchange.getIn().setBody(new DOMSource(resultXml));
            exchange.getIn().setHeader("status", 1);
        }

//        System.out.println("\n============================== Soap Message Processor End ===============================\n");

    }
}
