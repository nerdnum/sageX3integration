package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "GRP")
public class Group {

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String id;


    @JacksonXmlProperty(localName = "FLD")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Field> fields;

    @JacksonXmlProperty(localName = "LST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ItemList> itemLists;

    public Group() {
    }

    public Group(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<ItemList> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<ItemList> itemLists) {
        this.itemLists = itemLists;
    }

    @Override
    public String toString() {
        return "\n    Group{" +
                "id='" + id + '\'' +
                ", fields=" + fields +
                ", itemLists=" + itemLists +
                '}';
    }

    public void addField(Field theField){
        if (fields == null){
            fields = new ArrayList<>();
        }

        fields.add(theField);

    }

    public void addItemList(ItemList itemList){
        if (itemLists == null){
            itemLists = new ArrayList<>();
        }

        itemLists.add(itemList);

    }
}
