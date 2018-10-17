package na.com.meatco.sageX3integration.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.sageX3.Group;

import java.io.IOException;

public class TryListObject {

    public static void main(String[] args) throws IOException {

        String itemsXml = "<GRP ID=\"SIH1_3\">\n" +
                "        <LST NAME=\"REP\" SIZE=\"2\" TYPE=\"Char\">\n" +
                "            <ITM>NORWAY</ITM>\n" +
                "            <ITM/>\n" +
                "        </LST>\n" +
                "        <LST NAME=\"ZREP\" SIZE=\"2\" TYPE=\"Char\">\n" +
                "            <ITM>Norway</ITM>\n" +
                "            <ITM/>\n" +
                "        </LST>\n" +
                "    </GRP>";

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Group itemList = mapper.readValue(itemsXml, Group.class);

//        ItemList itemList = new ItemList();
//        itemList.addItem(new Item("NORWAY"));
//        itemList.addItem(new Item("CANADA"));
//        itemList.addItem(new Item("NAMIBIA"));


        System.out.println(mapper.writeValueAsString(itemList));

        ObjectMapper jsonMapper = new ObjectMapper();
        System.out.println(jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(itemList));

        System.out.println(itemList);
    }


}
