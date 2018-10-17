package na.com.meatco.sageX3integration.routes;

import na.com.meatco.sageX3integration.processors.PrettyXmlStringProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePrettyXmlRoute extends RouteBuilder {
    /*

    This class receives xml data in the following formats:
        javax.xml.transform.dom.DOMSource
        org.w3c.dom.Document
        java.lang.String

    It then transforms the data into "pretty" indented human readable xml data
    and save the "pretty" xml data to data/${header.fileName}

     */

    @Autowired
    PrettyXmlStringProcessor prettyXmlStringProcessor;

    @Override
    public void configure() throws Exception {

        from("{{xmlInputRoute}}")
                .process(prettyXmlStringProcessor)
//                .log("The body is \n${body}")
                .to("{{xmlOutputRoute}}");

    }
}
