package webserver.back.requestDataMaker;

import webserver.front.data.HttpRequest;

import java.util.Map;

public class RequestParamParser implements RequestParser{
    private Map<String,String> data;
    private boolean ifContainsData;

    @Override
    public void parseRequest(HttpRequest httpRequest) {
        String[] uriSeperated = httpRequest.getUrl().split("\\?");
        if(uriSeperated.length <= 1){
           data= null;
           ifContainsData= false;
           return;
        } //param이 없을 때
        data =ParamParser.parseParam(uriSeperated[1]);
    }

    @Override
    public Map<String, String> getDatas() {
        return data;
    }

    @Override
    public boolean ifContainsData() {
        return ifContainsData;
    }

    @Override
    public void putDataToRequestData(ParsedRequestData parsedRequestData) {
        parsedRequestData.setParameters(data);
    }

    public String[] getPathNotChanging(String path) {
        String[] uriSeperated = path.split("\\?");
        return uriSeperated[0].split("/");
    }
}
