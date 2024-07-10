package webserver.back.fileFounder;

import webserver.back.byteReader.Body;
import webserver.back.byteReader.ResponseFileBody;
import webserver.back.contentType.ContentTypeMaker;

import java.io.FileNotFoundException;

public class TemplateFileFounder implements FileFounder {
    private final String FILE_BASE_URL ="./src/main/resources/templates";
    @Override
    public Body findFile(String fileUrl) throws FileNotFoundException {
        String contentType = ContentTypeMaker.getContentType(fileUrl);
        return new ResponseFileBody(FILE_BASE_URL + fileUrl,contentType);
    }

}
