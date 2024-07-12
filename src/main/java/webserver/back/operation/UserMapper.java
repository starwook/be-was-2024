package webserver.back.operation;

import webserver.back.Error.UserNotFoundException;
import webserver.back.Error.WrongDataFormatException;
import webserver.back.byteReader.ResponseErrorBody;
import webserver.back.byteReader.ResponseJsonBody;
import webserver.back.data.StatusCode;
import webserver.back.db.Database;
import webserver.back.dynamic.DynamicHtmlChanger;
import webserver.back.model.User;
import webserver.back.session.Session;
import webserver.front.data.HttpRequest;
import webserver.front.data.HttpResponse;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class UserMapper {

    private final DynamicHtmlChanger dynamicHtmlChanger = new DynamicHtmlChanger();
    public HttpResponse addUser(Map<String,String> bodyVariables) throws WrongDataFormatException {
        User user = User.newUser(bodyVariables);
        User newUser = Database.addUser(user);

        ResponseJsonBody body =new  ResponseJsonBody(newUser);
        return HttpResponseMaker.makeHttpResponse(body,StatusCode.FOUND.getMessage(),"/index.html");
    }
    public HttpResponse showUserList(HttpRequest request,String url) throws FileNotFoundException {
        if(request.getSid().isEmpty()){
            return HttpResponseMaker.makeHttpResponse(new ResponseErrorBody(StatusCode.BAD_REQUEST.getMessage()),StatusCode.FOUND.getMessage(),"/user/login.html");
        }
        List<User> users =  Database.findAllUsers();
        byte[] bytes = dynamicHtmlChanger.showUserList(users,url);
        return HttpResponseMaker.makeHttpResponse(bytes,"text/html",StatusCode.OK.getMessage());

    }
    public HttpResponse userLogin(Map<String,String> bodyVariables) throws WrongDataFormatException, UserNotFoundException {
        User user = User.findUser(bodyVariables);
        try{
            User loginUser = Database.loginUser(user);
            Session  session = new Session(loginUser.getUserId());
            Database.saveSession(session);
            return HttpResponseMaker.makeHttpResponse(new ResponseJsonBody(null),StatusCode.FOUND.getMessage(),session,"/index.html");
        }
        catch (UserNotFoundException e) {
            return HttpResponseMaker.makeHttpResponse(new ResponseErrorBody(e.getMessage()), StatusCode.FOUND.getMessage(),"/user/login_failed.html");
        }
        catch (Exception e){
            return HttpResponseMaker.makeHttpResponseError(StatusCode.ERROR.getMessage(),e.getMessage());
        }
    }
}
