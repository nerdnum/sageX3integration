package na.com.meatco.sageX3integration.learning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.sageX3.*;


public class LearnXmlObjectMapping {


    public static void main(String[] args) throws JsonProcessingException {

        Group sih0_1_grp = new Group("SIH0_1");
        sih0_1_grp.addField(new Field("SALFCY", "Char", "WHS"));
        sih0_1_grp.addField(new Field("SIVTYP", "Char", "INV"));
        sih0_1_grp.addField(new Field("NUM", "Char", "WHS2019004704"));

        Group sih1_1_grp = new Group("SIH1_1");
        sih1_1_grp.addField(new Field("BPCCORD", "Char", "CWUM001"));
        sih1_1_grp.addField(new Field("BPRPAY", "Char", "CWUM001"));
        sih1_1_grp.addField(new Field("BPRGRU", "Char", "CWUM001"));

        Line line1 = new Line(1);
        line1.addField(new Field("ITMREF", "Char", "O08007"));
        line1.addField(new Field("SAU", "Char", "KG"));
        line1.addField(new Field("QTY", "Decimal", "54.39"));
        line1.addField(new Field("GROPRI", "Decimal", "22.8"));
        line1.addField(new Field("CCE1", "Char", "WHS"));
        line1.addField(new Field("CCE2", "Char", "BEEF"));

        Line line2 = new Line(2);
        line2.addField(new Field("ITMREF", "Char", "O10007"));
        line2.addField(new Field("SAU", "Char", "KG"));
        line2.addField(new Field("QTY", "Decimal", "30.03"));
        line2.addField(new Field("GROPRI", "Decimal", "18.65"));
        line2.addField(new Field("CCE1", "Char", "WHS"));
        line2.addField(new Field("CCE2", "Char", "BEEF"));

        Table sih4_1 = new Table("SIH4_1", 300, 2);

        sih4_1.addLine(line1);
        sih4_1.addLine(line2);


        Table sih4_2 = new Table("SIH4_2", 300, 2);
        sih4_2.addLine(line1);
        sih4_2.addLine(line2);


        SageX3Object sageX3Object = new SageX3Object();

        sageX3Object.addGroup(sih0_1_grp);
        sageX3Object.addGroup(sih1_1_grp);
        sageX3Object.addDetail(sih4_1);
        sageX3Object.addDetail(sih4_2);


//        System.out.println(sageX3Object);

        ObjectMapper objectMapper = new XmlMapper();



        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(objectMapper.writeValueAsString(sageX3Object));

    }






}
