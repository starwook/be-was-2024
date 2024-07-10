package webserver.back.requestDataMaker;

import webserver.front.data.HttpRequest;

import java.util.HashMap;

public class RequestUrlValueParser implements RequestParser {

    @Override
    public void parseRequest(HttpRequest httpRequest) {

    }

    @Override
    public HashMap<String, String> getDatas() {
        return null;
    }

    @Override
    public boolean ifContainsData() {
        return false;
    }

    @Override
    public void putDataToRequestData(ParsedRequestData parsedRequestData) {

    }
}
