package na.com.meatco.sageX3integration.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mock")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@MockEndpoints
public class SavePrettyXmlRouteMockTest {

    @Autowired
    CamelContext context;

    @Autowired
    Environment environment;

    @Autowired
    protected CamelContext createCamelContext(){
        return context;
    }

    @EndpointInject(uri = "mock:prettyXmlOutput")
    MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testPrettyXmlRoute() throws InterruptedException {

        String expected = "<aroot>\r\n" +
                "    <akey>Akey is provided</akey>\r\n" +
                "    <avalue>A value is provided</avalue>\r\n" +
                "</aroot>\r\n";


        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(expected);

        String testContent = "<aroot><akey>Akey is provided</akey><avalue>A value is provided</avalue></aroot>";

        producerTemplate.requestBodyAndHeader(environment.getProperty("xmlInputRoute"), testContent, "env", "spring.profiles.avtive");

        mockEndpoint.assertIsSatisfied();


    }

}
