package webserver.back.requestDataMaker;

import webserver.front.data.HttpRequest;

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
        if(contentType.startsWith("application/x-www-form-urlencoded")) {
            data= parseParamBody(new String(body));
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

    @Override
    public void putDataToRequestData(ParsedRequestData parsedRequestData) {
        parsedRequestData.setBodyVariables(data);
    }

    public Map<String, String> parseParamBody(String body) {
        return ParamParser.parseParam(body);
    }
}
