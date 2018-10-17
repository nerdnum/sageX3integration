package na.com.meatco.sageX3integration.entities.sageX3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import na.com.meatco.sageX3integration.entities.sageX3.Item;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "LST")
public class ItemList {

    @JacksonXmlProperty(isAttribute = true, localName = "NAME")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "SIZE")
    private int size;

    @JacksonXmlProperty(isAttribute = true, localName = "TYPE")
    private String type;

    @JacksonXmlProperty(localName = "ITM")
    @JacksonXmlElementWrapper(useWrapping = false)
    java.util.List<Item> items;

    public ItemList() {
    }

    public ItemList(String name, int size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        if (items == null){
            items = new ArrayList<>();
        }

        items.add(item);
    }

    @Override
    public String toString() {
        return "\n          ItemList{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", items=" + items +
                '}';
    }
}
