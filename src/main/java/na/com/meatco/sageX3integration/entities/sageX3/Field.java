package na.com.meatco.sageX3integration.entities.sageX3;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;




@JacksonXmlRootElement(localName = "FLD")
public class Field {


    @JacksonXmlProperty(isAttribute = true, localName = "NAME")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "TYPE")
    private String type;

    @JacksonXmlProperty(isAttribute = true, localName = "MENULAB")
    private String menuLab;

    @JacksonXmlProperty(isAttribute = true, localName = "MENULOCAL")
    private String menuLocal;

    @JacksonXmlText
    private String value;


    public Field() {
    }

    public Field(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMenuLab() {
        return menuLab;
    }

    public void setMenuLab(String menuLab) {
        this.menuLab = menuLab;
    }

    public String getMenuLocal() {
        return menuLocal;
    }

    public void setMenuLocal(String menuLocal) {
        this.menuLocal = menuLocal;
    }

    @Override
    public String toString() {
        return "\n        Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                "}";
    }
}

