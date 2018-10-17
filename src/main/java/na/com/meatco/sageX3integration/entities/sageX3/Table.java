package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import na.com.meatco.sageX3integration.entities.sageX3.Line;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "TAB")
public class Table {

    @JacksonXmlProperty(isAttribute = true, localName = "DIM")
    private int dimension;

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String Id;

    @JacksonXmlProperty(isAttribute = true, localName = "SIZE")
    private int size;

    @JacksonXmlProperty(localName = "LIN")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Line> lineList;

    public Table() {
    }

    public Table(String id, int dimension, int size) {
        this.dimension = dimension;
        Id = id;
        this.size = size;
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public void addLine(Line theLine){

        if(lineList == null){
            lineList = new ArrayList<>();
        }

        lineList.add(theLine);

    }

    @Override
    public String toString() {
        return "\n    Table{" +
                "dimension=" + dimension +
                ", Id='" + Id + '\'' +
                ", size=" + size +
                ", lineList=" + lineList +
                '}';
    }
}
