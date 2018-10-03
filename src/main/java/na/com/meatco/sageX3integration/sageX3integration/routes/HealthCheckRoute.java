package na.com.meatco.sageX3integration.sageX3integration.routes;

import na.com.meatco.sageX3integration.sageX3integration.processors.HealthCheckProcessor;
import na.com.meatco.sageX3integration.sageX3integration.processors.MailProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HealthCheckRoute extends RouteBuilder {

    @Autowired
    HealthCheckProcessor healthCheckProcessor;

    @Autowired
    MailProcessor mailProcessor;

    @Override
    public void configure() throws Exception {
        from("{{healthRoute}}").routeId("healthRoute")
                .pollEnrich("http://localhost:8080/actuator/health")
                .process(healthCheckProcessor)
                .to("log:?level=INFO&showBody=true")
                .choice()
                    .when(header("error").isEqualTo(true))
                        .process(mailProcessor)
                .end();
    }
}
