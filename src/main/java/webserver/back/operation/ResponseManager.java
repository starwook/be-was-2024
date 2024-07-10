package webserver.back.operation;

import webserver.back.Error.WrongDataFormatException;
import webserver.back.requestDataMaker.ParsedRequestData;
import webserver.back.data.SignInForm;
import webserver.back.data.StatusCode;
import webserver.back.fileFounder.StaticFileFounder;
import webserver.back.byteReader.Body;
import webserver.back.requestDataMaker.RequestDataMaker;
import webserver.front.data.HttpRequest;
import webserver.front.data.HttpResponse;

import java.io.FileNotFoundException;

public class ResponseManager {
    private final UserMapper userMapper;
    public ResponseManager(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    public HttpResponse getResponse(HttpRequest httpRequest)  {
        HttpResponseMaker httpResponseMaker = new HttpResponseMaker();
        String message;
        String originalUrl = httpRequest.getUrl();
        Body body;
        try{
            String changedPath;
            ParsedRequestData parsedRequestData = RequestDataMaker.getRequestData(httpRequest);
            String pathWithOutData = parsedRequestData.getUrl();
            if (pathWithOutData.equals("/registration")) {
                changedPath = "/registration/index.html";
                body = new StaticFileFounder().findFile(changedPath);
                message = StatusCode.OK.getMessage();
                return httpResponseMaker.makeHttpResponse(body,message);
            }
            if (pathWithOutData.equals("/create") && httpRequest.getMethod().equals("POST")){
                SignInForm signInForm = new SignInForm(parsedRequestData.getBodyVariables());
                body = userMapper.addUser(signInForm);
                message =StatusCode.FOUND.getMessage();
                String location ="/index.html";
                return httpResponseMaker.makeHttpResponse(body,message,location);
            }
            //없는 리소스 표시(404)
            body = new StaticFileFounder().findFile(originalUrl);
            message =StatusCode.OK.getMessage();
            return httpResponseMaker.makeHttpResponse(body,message);
        }
        catch (FileNotFoundException e){ //404 Not Found를 위한 곳
            message =StatusCode.NOT_FOUND.getMessage();
            return httpResponseMaker.makeHttpResponseError(message,message);
        }
        catch (WrongDataFormatException e){
            message = StatusCode.BAD_REQUEST.getMessage();
            return httpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
        catch (Exception e){
            message = StatusCode.ERROR.getMessage();
            e.printStackTrace();
            return httpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
    }
}

