package webserver.back.requestDataMaker;

import java.util.HashMap;
import java.util.Map;

public class ParamParser {

    public static Map<String,String> parseParam(String parameter) {
        String[] paramSeperated = parameter.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : paramSeperated) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
