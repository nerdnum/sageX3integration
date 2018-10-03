package na.com.meatco.sageX3integration.sageX3integration.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BasicFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:data/input?noop=true")
                .log("Processing: ${headers.CamelFileNameOnly}")
                .to("file:data/output");

    }
}
