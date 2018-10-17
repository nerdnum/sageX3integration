package na.com.meatco.sageX3integration.config;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Configuration
public class SageX3SoapConfig {


    @Value("${client.default-uri}")
    private String defaultUri;

    @Value("${client.user.name}")
    private String userName;

    @Value("${client.user.password}")
    private String userPassword;



    @Bean(value="sageX3SoapConnection")
    public WebServiceTemplate createWebserviceTemplate(){

        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();

        HttpClient httpClient = HttpClientBuilder.create()
                .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
                .setDefaultHeaders(soapAuthorizationHeaders())
                .build();

        messageSender.setHttpClient(httpClient);

        WebServiceTemplate template = new WebServiceTemplate();
        template.setDefaultUri(defaultUri);
        template.setMessageSender(messageSender);
        return template;
    }


    public List<Header> soapAuthorizationHeaders(){

        String auth =  userName + ":" + userPassword;
        System.out.println("Auth credentials are: " + auth);
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        List<Header> headersList = new ArrayList<>();
        headersList.add(new BasicHeader(HttpHeaders.AUTHORIZATION, authHeader));

        return headersList;
    }

}
