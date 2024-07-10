package webserver.front.data;

import java.util.HashMap;
import java.util.Map;

public class HttpMessage {
    private String httpVersion;
    private RepresentationHeader representationHeader;
    private byte[] body;
    private boolean containsBody;
    public HttpMessage(String httpVersion, byte[] body,String contentType) {
        this.httpVersion = httpVersion;
        this.representationHeader = new RepresentationHeader(contentType,body.length);
        this.body = body;
        if(body.length > 0) { containsBody = true;}
        else { containsBody = false; }
    }

    public boolean containsBody() {
        return containsBody;
    }

    public HttpMessage(String httpVersion) {
        this.httpVersion = httpVersion;
    }
    public String getHttpVersion() {
        return httpVersion;
    }
    public Map<String, String> getRepresentationHeader() {
        return representationHeader.getData();
    }
    public String getContentType(){
        return representationHeader.getContentType();
    }
    public String getContentLength(){
        return representationHeader.getContentLength();
    }

    public byte[] getBody() {
        return body;
    }
}
class RepresentationHeader{
    private final Map<String,String> data;
    private final String contentTypeKey ="Content-Type";
    private final String contentLengthKey ="Content-Length";
    public RepresentationHeader(String contentType, int contentLength){
        this.data = new HashMap<>();
        this.data.put(contentTypeKey, contentType+"; charset=utf-8");
        this.data.put(contentLengthKey, String.valueOf(contentLength));
    }
    public Map<String, String> getData() {
        return data;
    }
    public String getContentType(){
        return data.get(contentTypeKey);
    }
    public String getContentLength(){return data.get(contentLengthKey);}
}

