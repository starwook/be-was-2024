package webserver.back.requestDataMaker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestData {
    private String[] pathNotChanging;
    private  boolean ifAdditionalInformationExist;
    private Map<String, String> parameters;
    private Map<String, String> pathVariables;
    private Map<String, String> bodyVariables;

    public RequestData() {

    }

    public void setParameters(RequestParser requestParser){
        this.parameters = requestParser.getDatas();

    }
    public void setPathVariables(RequestParser requestParser){
        this.pathVariables = requestParser.getDatas();
    }
    public void setBodyVariables(RequestParser requestParser){
        this.bodyVariables = requestParser.getDatas();
    }

    public String getUrl() {
        StringBuilder path = new StringBuilder();
        for(String str : pathNotChanging){
            path.append("/").append(str);
        }
        return path.toString();
    }

    public Map<String, String> getParameters() {
        return parameters;
    }


    public boolean isIfAdditionalInformationExist() {
        return ifAdditionalInformationExist;
    }

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public Map<String, String> getBodyVariables() {
        return bodyVariables;
    }

    public boolean IfAdditionalInformationExist() {
        return ifAdditionalInformationExist;
    }

    public void setPathNotChanging(String[] pathNotChanging) {
        this.pathNotChanging = Arrays.copyOfRange(pathNotChanging,1, pathNotChanging.length);
    }
}
