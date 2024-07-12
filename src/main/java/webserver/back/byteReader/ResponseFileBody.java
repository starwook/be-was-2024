package webserver.back.byteReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResponseFileBody implements Body {
    private FileInputStream fileInputStream;
    private String contentType;
    public ResponseFileBody(String fileUrl, String contentType) throws FileNotFoundException {
        this.fileInputStream = new FileInputStream(fileUrl);
        this.contentType = contentType;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public byte[] makeBytes()  {
        try{
            return fileInputStream.readAllBytes();
        }
        catch(IOException e){
            return new byte[0];
        }
    }
    public String makeString(){
        return fileInputStream.toString();
    }
}
