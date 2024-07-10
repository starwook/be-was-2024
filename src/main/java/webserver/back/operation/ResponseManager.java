package webserver.back.operation;

import webserver.back.Error.UserNotFoundException;
import webserver.back.Error.WrongDataFormatException;
import webserver.back.fileFounder.TemplateFileFounder;
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

        String message;
        String originalUrl = httpRequest.getUrl();
        Body body;
        try{
            String changedPath;
            ParsedRequestData parsedRequestData = RequestDataMaker.getRequestData(httpRequest);
            String pathWithOutData = parsedRequestData.getUrl();
            if (pathWithOutData.equals("/registration")&&httpRequest.getMethod().equals("GET")) {
                changedPath = "/registration/index.html";

                body = new StaticFileFounder().findFile(changedPath);
                message = StatusCode.OK.getMessage();
                return HttpResponseMaker.makeHttpResponse(body,message);
            }
            if (pathWithOutData.equals("/create") && httpRequest.getMethod().equals("POST")){
                return userMapper.addUser(parsedRequestData.getBodyVariables());
            }
            if(pathWithOutData.equals("/user/login.html") &&httpRequest.getMethod().equals("GET")){
                body = new TemplateFileFounder().findFile(pathWithOutData);
                message = StatusCode.OK.getMessage();
                return HttpResponseMaker.makeHttpResponse(body,message);
            }
            if(pathWithOutData.equals("/user/login") &&httpRequest.getMethod().equals("POST")){
                return userMapper.userLogin(parsedRequestData.getBodyVariables());
            }
            if(pathWithOutData.equals("/user/login_failed.html")&&httpRequest.getMethod().equals("GET")){
                body = new TemplateFileFounder().findFile(pathWithOutData);
                message = StatusCode.OK.getMessage();
                return HttpResponseMaker.makeHttpResponse(body,message);
            }
            //없는 리소스 표시(404)
            body = new StaticFileFounder().findFile(originalUrl);
            message =StatusCode.OK.getMessage();
            return HttpResponseMaker.makeHttpResponse(body,message);
        }
        catch (UserNotFoundException e){
            message = StatusCode.METHOD_NOT_ALLOWED.getMessage();
            return HttpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
        catch (FileNotFoundException e){ //404 Not Found를 위한 곳
            message =StatusCode.NOT_FOUND.getMessage();
            return HttpResponseMaker.makeHttpResponseError(message,message);
        }
        catch (WrongDataFormatException e){
            message = StatusCode.BAD_REQUEST.getMessage();
            return HttpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
        catch (Exception e){
            message = StatusCode.ERROR.getMessage();
            e.printStackTrace();
            return HttpResponseMaker.makeHttpResponseError(message,e.getMessage());
        }
    }
}

