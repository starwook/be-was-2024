package webserver.back.requestDataMaker;
import webserver.front.data.HttpRequest;

public class RequestDataMaker {
    public static ParsedRequestData getRequestData(HttpRequest httpRequest) {
        ParsedRequestData parsedRequestData = new ParsedRequestData();
        RequestBodyParser requestBodyParser  = (RequestBodyParser) parseData(new RequestBodyParser(),httpRequest, parsedRequestData);
        RequestParamParser requestParamParser = (RequestParamParser) parseData(new RequestParamParser(),httpRequest, parsedRequestData);
        parsedRequestData.setPathNotChanging(requestParamParser.getPathNotChanging(httpRequest.getUrl()));
        return parsedRequestData;
    }

    private static RequestParser parseData(RequestParser requestParser, HttpRequest httpRequest, ParsedRequestData parsedRequestData) {
        requestParser.parseRequest(httpRequest);
        requestParser.putDataToRequestData(parsedRequestData);
        return requestParser;
    }
}
