package na.com.meatco.sageX3integration.routes;

import na.com.meatco.sageX3integration.entities.sageX3.SageX3Object;
import na.com.meatco.sageX3integration.processors.SageX3SoapMessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SageX3SoapQueryToObjectRoute extends RouteBuilder {

    @Autowired
    SageX3SoapMessageProcessor soapMessageProcessor;

    @Autowired
    Environment environment;

    @Override
    public void configure() throws Exception {

        JacksonXMLDataFormat sageX3ObjectFormat = new JacksonXMLDataFormat();
        sageX3ObjectFormat.setUnmarshalType(SageX3Object.class);

        from("direct:sageX3SoapRequestInput")
                .log("SoapRequest: ${body}")
                .to("spring-ws:{{client.default-uri}}&soapAction=readRequest")
                .setHeader("fileName").simple("output/test/sageX3ResponseMessage.xml")
                .wireTap("direct:prettyXml")
                .transform().xpath("/")
                .process(soapMessageProcessor)
                .choice()
                    .when(header("status").isEqualTo(0))
                        .log("Sage X3 Request returned the following error messages: \n${body}")
                    .when(header("status").isEqualTo(1))
                        .unmarshal(sageX3ObjectFormat);
//                        .marshal().string()
//                        .to("file:data/output/xml?fileName=invoice-${date:now:yyyyMMddHHmmss}.txt")
//                        .log("Found X3 object ${body}")
//                .end();
//                .to("mock:sageX3SoapRequestOutput");
    }
}
