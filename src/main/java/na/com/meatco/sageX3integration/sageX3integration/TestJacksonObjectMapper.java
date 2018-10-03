package na.com.meatco.sageX3integration.sageX3integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.plugin2.message.JavaScriptBaseMessage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestJacksonObjectMapper {

    private static class Car {

        private String color;
        private String make;

        public Car(){
            //Default constructor in needed otherwise Jackson unmarshalling won't work.
        }

        public Car(String color, String make){
            this.color = color;
            this.make= make;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "color='" + color + '\'' +
                    ", make='" + make + '\'' +
                    '}';
        }
    }



    public static void main(String[] args) {
        String springJson = "{\"status\":\"DOWN\",\"details\":{\"camel\":{\"status\":\"UP\",\"details\":{\"name\":\"camel-1\",\"version\":\"2.22.1\",\"uptime\":\"1.496 seconds\",\"uptimeMillis\":1496,\"status\":\"Started\"}},\"diskSpace\":{\"status\":\"UP\",\"details\":{\"total\":499677917184,\"free\":216835846144,\"threshold\":10485760}},\"db\":{\"status\":\"DOWN\",\"details\":{\"database\":\"PostgreSQL\",\"hello\":1}},\"mail\":{\"status\":\"UP\",\"details\":{\"location\":\"192.168.10.246:25\"}}}}";
        ObjectMapper objectMapper = new ObjectMapper();


//

        try {

            JsonNode actualObj = objectMapper.readTree(springJson);

            System.out.println(actualObj);

            if(actualObj.findValue("status").asText().equals("DOWN")){

                Iterator<Map.Entry<String,JsonNode>> elements = actualObj.get("details").fields();

                while(elements.hasNext()){
                    Map.Entry<String,JsonNode> element = elements.next();
                    String key = element.getKey();
                    JsonNode value = element.getValue();
                    if(value.findValue("status").asText().equals("DOWN")){
                        System.out.println(key + " is down.");
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }
}