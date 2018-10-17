package na.com.meatco.sageX3integration.routes;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mock")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SavePrettyXmlRouteTest {

    @Autowired
    Environment environment;

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testPrettyXmlRoute(){

        String expected = "<aroot>\n" +
                "    <akey>Akey is provided</akey>\n" +
                "    <avalue>A value is provided</avalue>\n" +
                "</aroot>";

        String testContent = "<aroot><akey>Akey is provided</akey><avalue>A value is provided</avalue></aroot>";

        producerTemplate.sendBody("direct:prettyXml", testContent);


    }

}
