package na.com.meatco.sageX3integration.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;
import org.junit.Assert;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SageX3RequestFactoryTest {

    SageX3RequestFactory sageX3RequestFactory = new SageX3RequestFactory();

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryIllegalAction(){

        System.out.println("Running Sage X3 action test");
        sageX3RequestFactory.setAction("execute");

    }

    @Test
    public void testFactoryXml() throws IOException, ParserConfigurationException, SAXException {

        String expected = "<read>\r\n" +
                "  <publicName>Z_EDI_INV</publicName>\r\n" +
                "  <objectXml/>\r\n" +
                "  <callContext>\r\n" +
                "    <codeLang>ENG</codeLang>\r\n" +
                "    <poolAlias>TESTMCN</poolAlias>\r\n" +
                "    <poolId/>\r\n" +
                "    <requestConfig/>\r\n" +
                "  </callContext>\r\n" +
                "  <objectKeys>\r\n" +
                "    <sequence>\r\n" +
                "      <key>NUM</key>\r\n" +
                "      <value>WHS2019004707</value>\r\n" +
                "    </sequence>\r\n" +
                "  </objectKeys>\r\n" +
                "</read>\r\n";


        SageX3RequestFactory sageX3RequestFactory = new SageX3RequestFactory();
        sageX3RequestFactory.setCallContext("ENG", "TESTMCN", null, null);
        sageX3RequestFactory.setPublicName("Z_EDI_INV");
        sageX3RequestFactory.setAction("read");
        sageX3RequestFactory.addObjectKeySequence("NUM", "WHS2019004707");

        String x3xml = sageX3RequestFactory.getSageX3RequestXml();

        Assert.assertEquals(expected,x3xml);
    }

}
