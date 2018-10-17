package na.com.meatco.sageX3integration.processors;

import na.com.meatco.sageX3integration.utils.XmlPrettyPrint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMSource;

@Component
public class PrettyXmlStringProcessor implements Processor {
    /*

    This class serves as an adapter between a camel route and the
    na.com.meatco.sageX3integration.utils.XmlPrettyPrint class.

    It receives data in the body of the exchange in the following formats:
        javax.xml.transform.dom.DOMSource
        org.w3c.dom.Document
        java.lang.String

    Using the helper class it then transforms the data into "pretty" indented
    human readable xml data and returns the "pretty" xml to the exchange
    in java.lang.String format}

     */


    @Override
    public void process(Exchange exchange) throws Exception {

        Object object = exchange.getIn().getBody();

        String prettyXmlString = "";
        XmlPrettyPrint xpp = new XmlPrettyPrint();

        if (object.getClass().getName().equals("javax.xml.transform.dom.DOMSource")){
            DOMSource source = (DOMSource) object;
            prettyXmlString = xpp.toPrettyString(source, 4);
        } else if(object.getClass().getName().equals("org.w3c.dom.Document")) {
            Document document = (Document) object;
            prettyXmlString = xpp.toPrettyString(document, 4);
        } else {
            String xmlString = (String) object;
            prettyXmlString = xpp.toPrettyString(xmlString, 4);
        }

        exchange.getIn().setBody(prettyXmlString);

    }
}
