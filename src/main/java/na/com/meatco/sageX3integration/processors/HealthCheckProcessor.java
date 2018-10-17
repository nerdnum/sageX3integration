package na.com.meatco.sageX3integration.processors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class HealthCheckProcessor implements org.apache.camel.Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String healthCheckResult = exchange.getIn().getBody(String.class);
        log.info("Health check string of the APP is: \n" + healthCheckResult);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode healthJson = objectMapper.readTree(healthCheckResult);

        log.info("Map read is: " + healthJson);

        StringBuilder builder=null;

        if(healthJson.findValue("status").asText().equals("DOWN")){

            if (builder==null) {
                builder = new StringBuilder();
            }

            Iterator<Map.Entry<String,JsonNode>> elements = healthJson.get("details").fields();

            while(elements.hasNext()){
                Map.Entry<String,JsonNode> element = elements.next();
                String key = element.getKey();
                JsonNode value = element.getValue();
                log.info("The evaluated key is: " + key);
                if(value.findValue("status").asText().equals("DOWN")){
                    builder.append(key + " in camel server is down.");
                }
            }
        }

        if(builder!=null){
            log.info("Exception message is: " + builder.toString());
            exchange.getIn().setHeader("error", true);
            exchange.getIn().setBody(builder.toString());
            exchange.setProperty(Exchange.EXCEPTION_CAUGHT, builder.toString());
        }

    }
}
