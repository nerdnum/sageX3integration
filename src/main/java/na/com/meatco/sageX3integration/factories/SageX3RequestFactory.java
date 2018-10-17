package na.com.meatco.sageX3integration.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.sageX3.soap.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SageX3RequestFactory {

    private SageX3Request sageX3Request;

    private CallContext callContext;

    private List<ObjectKeySequence> objectKeySequences;

    private String publicName;

    private String objectXml;

    private String action;

    private String soapAction;

    public SageX3RequestFactory() {

        sageX3Request = new SageX3Request();

    }

    public CallContext getCallContext() {
        return callContext;
    }

    public void setCallContext(CallContext callContext) {
        this.callContext = callContext;
        sageX3Request.setCallContext(this.callContext);
    }

    public void setCallContext(String codeLang, String poolAlias, String poolId, String requestContext) {

        CallContext callContext = new CallContext(codeLang, poolAlias, poolId, requestContext);
        this.callContext = callContext;
        sageX3Request.setCallContext(this.callContext);

    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
        sageX3Request.setPublicName(this.publicName);
    }

    public String getPublicName() {
        return publicName;
    }

    public List<ObjectKeySequence> getObjectKeySequences() {
        return objectKeySequences;
    }

    public void setObjectKeySequences(List<ObjectKeySequence> objectKeySequences) {
        this.objectKeySequences = objectKeySequences;
        sageX3Request.setObjectKeySequences(objectKeySequences);
    }

    public void addObjectKeySequence(ObjectKeySequence newObjectKeySequence){
        if (this.objectKeySequences == null)
        {
            this.objectKeySequences = new ArrayList<>();
        }
        this.objectKeySequences.add(newObjectKeySequence);
        sageX3Request.setObjectKeySequences(this.objectKeySequences);
    }

    public void addObjectKeySequence(String key, String value){
        ObjectKeySequence objectKeySequence = new ObjectKeySequence();
        ObjectKey objectKey = new ObjectKey(key, value);
        objectKeySequence.setObjectKey(objectKey);
        this.addObjectKeySequence(objectKeySequence);
    }

    public void setObjectXml(String objectXml) {
        this.objectXml = objectXml;
        sageX3Request.setObjectXml(this.objectXml);
    }

    public String getObjectXml() {
        return objectXml;
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {

        if(!SageX3Actions.actions.containsKey(action))
            throw new IllegalArgumentException("\"" + action + "\" is not a valid SageX3 Webservice action");

        this.action = action;
        this.soapAction = action + "Request";
    }

    public String getSoapAction() {
        return soapAction;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public String getSageX3RequestXml() throws IOException, ParserConfigurationException, SAXException {
        if (this.action == null){
            throw new NullPointerException("Sage X3 action not set");
        }

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String xml = mapper.writeValueAsString(this.sageX3Request);

        xml = xml.replace("SageX3Request", this.action);

        return xml;

    }
}
