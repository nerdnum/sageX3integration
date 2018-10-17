package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "ITM")
public class Item {

    @JacksonXmlText
    private String value;

    public Item() {
    }

    public Item(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n            Item{" +
                "value='" + value + '\'' +
                '}';
    }
}
