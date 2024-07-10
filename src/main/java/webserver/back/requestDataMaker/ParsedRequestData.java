package webserver.back.requestDataMaker;

import java.util.Arrays;
import java.util.Map;

public class ParsedRequestData {
    private String[] pathNotChanging;
    private  boolean ifAdditionalInformationExist;
    private Map<String, String> parameters;
    private Map<String, String> pathVariables;
    private Map<String, String> bodyVariables;

    public ParsedRequestData() {

    }

    public void setParameters(Map<String, String> parameters){
        this.parameters = parameters;
    }
    public void setPathVariables(Map<String, String> pathVariables){
        this.pathVariables = pathVariables;
    }
    public void setBodyVariables(Map<String, String> bodyVariables) {
        this.bodyVariables = bodyVariables;
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
