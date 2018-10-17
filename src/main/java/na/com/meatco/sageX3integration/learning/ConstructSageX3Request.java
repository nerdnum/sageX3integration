package na.com.meatco.sageX3integration.learning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import na.com.meatco.sageX3integration.entities.sageX3.soap.CallContext;
import na.com.meatco.sageX3integration.entities.sageX3.soap.ObjectKey;
import na.com.meatco.sageX3integration.entities.sageX3.soap.ObjectKeySequence;
import na.com.meatco.sageX3integration.entities.sageX3.soap.SageX3Request;

import java.util.ArrayList;
import java.util.List;

public class ConstructSageX3Request {

    public static void main(String[] args) throws JsonProcessingException {

        CallContext callContext = new CallContext("ENG", "TESTMCN", "", "");

        ObjectKeySequence objectKeySequence = new ObjectKeySequence(new ObjectKey("NUM", "WHS2019004707"));

        List<ObjectKeySequence> sequences = new ArrayList<>();
        sequences.add(objectKeySequence);

        SageX3Request request  = new SageX3Request(callContext, "Z_EDI_INV", sequences);

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(mapper.writeValueAsString(request));


    }

}
