package na.com.meatco.sageX3integration.entities.sageX3.soap;

import java.util.HashMap;
import java.util.Map;

public class SageX3Actions {

    public static final Map<String, String> actions = new HashMap<>();

    static {

        // run, save, delete, read, query, modify, getDescription

        actions.put("run", "runAction");
        actions.put("save", "saveAction");
        actions.put("delete", "deleteAction");
        actions.put("read", "readAction");
        actions.put("query", "queryAction");
        actions.put("modify", "modifyAction");
        actions.put("getDescription", "getDescriptionAction");
    }


}
