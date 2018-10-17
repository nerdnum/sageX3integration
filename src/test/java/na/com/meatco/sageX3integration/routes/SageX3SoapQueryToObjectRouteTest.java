package na.com.meatco.sageX3integration.routes;

import na.com.meatco.sageX3integration.entities.sageX3.SageX3Object;
import na.com.meatco.sageX3integration.factories.SageX3RequestFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@MockEndpoints
public class SageX3SoapQueryToObjectRouteTest {

    @Autowired
    CamelContext context;

    @Autowired
    Environment environment;

    @Autowired
    protected CamelContext createCamelContext(){
        return context;
    }

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testSageX3SoapQueryAndObjectUnmarshalling() throws InterruptedException, ParserConfigurationException, SAXException, IOException {

        String testInvoiceNumber = "WHS2019004707";
//        String testInvoiceNumber = "WHS2019004"; // Non-existant invoice

        SageX3RequestFactory sageX3RequestFactory = new SageX3RequestFactory();
        sageX3RequestFactory.setCallContext("ENG", "TESTMCN", null, null);
        sageX3RequestFactory.setPublicName("Z_EDI_INV");
        sageX3RequestFactory.setAction("read");
        sageX3RequestFactory.addObjectKeySequence("NUM", testInvoiceNumber);

        String soapCall = sageX3RequestFactory.getSageX3RequestXml();
        String soapAction = sageX3RequestFactory.getSoapAction();

        producerTemplate.setDefaultEndpointUri(environment.getProperty("sageX3SoapRequestInput"));

        SageX3Object sageX3Object = (SageX3Object) producerTemplate.requestBodyAndHeader(soapCall, "soapAction", soapAction);

        String receivedInvoiceNumber = sageX3Object.getHeader().get(0).getFields().get(4).getValue();

        Assert.assertEquals(testInvoiceNumber, receivedInvoiceNumber);

    }

}
