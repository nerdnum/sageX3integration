package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import na.com.meatco.sageX3integration.entities.sageX3.Field;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "LIN")
public class Line {

    @JacksonXmlProperty(isAttribute = true, localName = "NUM")
    private int num;

    @JacksonXmlProperty(localName = "FLD")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Field> fieldList;

    public Line() {
    }

    public Line(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public void addField(Field theField){
        if (fieldList == null){
            fieldList = new ArrayList<>();
        }

        fieldList.add(theField);

    }

    @Override
    public String toString() {
        return "\n      Line{" +
                "num='" + num + '\'' +
                ", fieldList=" + fieldList +
                "}";
    }


}
