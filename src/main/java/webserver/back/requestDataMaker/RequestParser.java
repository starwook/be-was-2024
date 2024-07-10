package webserver.back.requestDataMaker;

import webserver.front.data.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public interface RequestParser {

    void parseRequest(HttpRequest httpRequest);
    Map<String, String> getDatas();
    boolean ifContainsData();
}
