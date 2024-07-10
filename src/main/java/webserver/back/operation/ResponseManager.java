package webserver.back.operation;

import webserver.back.Error.WrongDataFormatException;
import webserver.back.requestDataMaker.RequestData;
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
    public HttpResponse getResponse(HttpRequest request)  {
        HttpResponseMaker httpResponseMaker = new HttpResponseMaker();
        String message;
        String originalUrl = request.getUrl();
        Body body;
        try{
            String changedPath;
            RequestData requestData = RequestDataMaker.getRequestData(request);
            String pathWithOutData = requestData.getUrl();
            System.out.println(pathWithOutData);
            if (pathWithOutData.equals("/registration")) {
                changedPath = "/registration/index.html";
                body = new StaticFileFounder().findFile(changedPath);
                message = StatusCode.OK.getMessage();
                return httpResponseMaker.makeHttpResponse(body,message);
            }
            if (pathWithOutData.equals("/create")) {
                System.out.println(requestData.getBodyVariables().size());
                SignInForm signInForm = new SignInForm(requestData.getBodyVariables());
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
            return httpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
    }
}

