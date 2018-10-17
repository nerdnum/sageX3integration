package na.com.meatco.sageX3integration.entities.sageX3.soap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement
public class SageX3Request {

    @JacksonXmlProperty(localName = "callContext")
    private CallContext callContext;

    @JacksonXmlProperty
    private String publicName;

    @JacksonXmlProperty(localName = "objectKeys")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<ObjectKeySequence> objectKeySequences;

    @JacksonXmlProperty
    private String objectXml;

    public SageX3Request() {
    }

    public SageX3Request(CallContext callContext, String publicName, List<ObjectKeySequence> objectKeySequences) {
        this.callContext = callContext;
        this.publicName = publicName;
        this.objectKeySequences = objectKeySequences;
    }

    public CallContext getCallContext() {
        return callContext;
    }

    public void setCallContext(CallContext callContext) {
        this.callContext = callContext;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public List<ObjectKeySequence> getObjectKeySequences() {
        return objectKeySequences;
    }

    public void setObjectKeySequences(List<ObjectKeySequence> objectKeySequences) {
        this.objectKeySequences = objectKeySequences;
    }

    public void addObjectKeySequence(ObjectKeySequence newObjectKeySequence){
        if (objectKeySequences == null) {
            objectKeySequences = new ArrayList<>();
        }
        objectKeySequences.add(newObjectKeySequence);
    }

    public String getObjectXml() {
        return objectXml;
    }

    public void setObjectXml(String objectXml) {
        this.objectXml = objectXml;
    }

    @Override
    public String toString() {
        return "SageX3Request{" +
                "callContext=" + callContext +
                ", publicName='" + publicName + '\'' +
                ", objectKeySequences=" + objectKeySequences +
                ", objectXml='" + objectXml + '\'' +
                '}';
    }
}
