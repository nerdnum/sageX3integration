package na.com.meatco.sageX3integration.entities.sageX3.soap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "objectKey")
public class ObjectKey {

    @JacksonXmlProperty
    private String key;

    @JacksonXmlProperty
    private String value;

    public ObjectKey() {
    }

    public ObjectKey(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ObjectKey{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
