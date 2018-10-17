package na.com.meatco.sageX3integration.learning;


import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import na.com.meatco.sageX3integration.utils.XmlPrettyPrint;

public class LearnJavaHttpClientMain {

    public static String soapCall = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wss=\"http://www.adonix.com/WSS\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">\n"+
            "   <soapenv:Header/>\n"+
            "   <soapenv:Body>\n"+
            "      <read>\n"+
            "         <callContext>\n"+
            "            <codeLang>ENG</codeLang>\n"+
            "            <poolAlias>TESTMCN</poolAlias>\n"+
            "            <poolId></poolId>\n"+
            "            <requestConfig>adxwss.trace.on=off&adxwss.trace.size=16384&adonixx.trace.on=off&adonix.trace.level=1&adonix.trace.size=8&adxwss.optreturn=XML&adxwss.beautify=true</requestConfig>\n"+
            "         </callContext>\n"+
            "         <publicName>Z_EDI_INV</publicName>\n"+
            "         <objectKeys>\n"+
            "           <sequence>\n"+
            "               <key>NUM</key>\n"+
            "               <value>WHS2019004707</value>\n"+
            "           </sequence>\n"+
            "         </objectKeys>\n"+
            "      </read>\n"+
            "   </soapenv:Body>\n"+
            "</soapenv:Envelope>";

    // Sales Order SORDER SOHNUM WHS2015SON000030

    // Invoices: ZINVOICES NUM WHS2019004704

    public static void main(String[] args) {

        try {

            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("WEBS", "WEBS001");
            provider.setCredentials(AuthScope.ANY, credentials);

            CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
            HttpPost request = new HttpPost("http://192.168.10.50:8124/soap-generic/syracuse/collaboration/syracuse/CAdxWebServiceXmlCC");

            request.setEntity(new StringEntity(soapCall));
            request.addHeader(new BasicScheme().authenticate(credentials, request, null));
            request.setHeader("soapAction", "readRequest");
            request.setHeader(HttpHeaders.CONTENT_TYPE,"text/xml");
            request.setHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-8");

            XmlPrettyPrint xmlp = new XmlPrettyPrint();

            CloseableHttpResponse response = client.execute(request);
            String bodyAsString = EntityUtils.toString(response.getEntity());
            System.out.println(xmlp.toPrettyString(bodyAsString, 4));
            client.close();

        }
        catch (AuthenticationException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}


