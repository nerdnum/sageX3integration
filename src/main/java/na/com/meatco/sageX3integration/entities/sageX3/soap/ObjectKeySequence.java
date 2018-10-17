package na.com.meatco.sageX3integration.entities.sageX3.soap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "sequence")
public class ObjectKeySequence {

    @JacksonXmlProperty(localName = "sequence")
    private ObjectKey objectKey;

    public ObjectKeySequence() {
    }

    public ObjectKeySequence(ObjectKey objectKey) {
        this.objectKey = objectKey;
    }

    public ObjectKey getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(ObjectKey objectKey) {
        this.objectKey = objectKey;
    }

    @Override
    public String toString() {
        return "ObjectKeySequence{" +
                "objectKey=" + objectKey +
                '}';
    }
}
