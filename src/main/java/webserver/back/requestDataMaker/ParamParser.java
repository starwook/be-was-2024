package webserver.back.requestDataMaker;

import java.util.HashMap;

public class ParamParser {

    public static HashMap<String,String> parseParam(String parameter) {
        String[] paramSeperated = parameter.split("&");
        HashMap<String, String> map = new HashMap<>();
        for (String param : paramSeperated) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
