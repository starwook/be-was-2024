package webserver.back.requestDataMaker;

import webserver.front.data.HttpRequest;

public class RequestDataMaker {
    public static RequestData getRequestData(HttpRequest httpRequest) {
        RequestData requestData = new RequestData();
        RequestBodyParser requestBodyParser  = (RequestBodyParser) parseData(new RequestBodyParser(),httpRequest,requestData);
        RequestParamParser requestParamParser = (RequestParamParser) parseData(new RequestParamParser(),httpRequest, requestData);
        requestData.setPathNotChanging(requestParamParser.getPathNotChanging(httpRequest.getUrl()));
        return requestData;
    }
    private static RequestParser parseData(RequestParser requestParser, HttpRequest httpRequest, RequestData requestData) {
        requestParser.parseRequest(httpRequest);
        requestData.setParameters(requestParser);
        return requestParser;
    }
}
