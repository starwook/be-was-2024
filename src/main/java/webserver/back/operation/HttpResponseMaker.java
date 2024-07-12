package webserver.back.operation;

import webserver.back.byteReader.ResponseErrorBody;
import webserver.back.data.StatusCode;
import webserver.back.byteReader.Body;
import webserver.back.session.Session;
import webserver.front.data.HttpResponse;

public class HttpResponseMaker {
    public static HttpResponse makeHttpResponse(Body body, String message){
        return new HttpResponse("HTTP/1.1",
                StatusCode.getCode(message),
                message,
                body.makeBytes(),
                body.getContentType());
    }
    public static HttpResponse makeHttpResponse(byte[] body, String contentType ,String message){
        return new HttpResponse("HTTP/1.1",
                StatusCode.getCode(message),
                message,
                body,
                contentType);
    }
    public static HttpResponse makeHttpResponse(Body body, String message, String location){
        HttpResponse httpResponse = makeHttpResponse(body, message);
        httpResponse.addLocation(location);
        return httpResponse;
    }
    public static HttpResponse makeHttpResponseError(String message,String errorCause){
        Body body = new ResponseErrorBody(errorCause);
        return makeHttpResponse(body, message);
    }
    public static HttpResponse makeHttpResponse(Body body, String message, Session session,String location){
        HttpResponse httpResponse = makeHttpResponse(body, message);
        httpResponse.addLocation(location);
        httpResponse.addCookie("sid="+session.getSessionId(),"/");
        return httpResponse;
    }
}
