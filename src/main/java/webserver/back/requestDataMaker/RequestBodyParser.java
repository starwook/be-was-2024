package webserver.back.requestDataMaker;

import webserver.back.contentType.BaseContentType;
import webserver.back.contentType.ContentTypeMaker;
import webserver.front.data.HttpRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestBodyParser implements RequestParser{
    private Map<String,String> data;
    private boolean containsData =false;
    @Override
    public void parseRequest(HttpRequest httpRequest) {
        if(!httpRequest.containsBody()) {
            data =null;
            return;
        }
        String contentType = httpRequest.getContentType();
        byte[] body = httpRequest.getBody();
        if(contentType.equals("application/x-www-form-urlencoded")) {
            data= parseParamBody(Arrays.toString(body));
            containsData = true;
            return;
        }
        data =null;
    }

    @Override
    public Map<String, String> getDatas() {
        return data;
    }

    @Override
    public boolean ifContainsData() {
        return containsData;
    }

    public HashMap<String, String> parseParamBody(String body) {
        return ParamParser.parseParam(body);
    }
}
