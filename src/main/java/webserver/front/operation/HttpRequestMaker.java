package webserver.front.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.back.byteReader.RequestBody;
import webserver.front.data.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestMaker {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestMaker.class);
    public HttpRequest parseRequest(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String firstLine = br.readLine();

        FirstLine fl = getFirstLine(firstLine);
        logger.debug(firstLine);
        String contentType = "";
        while(true){
            String line = br.readLine();
            if(line.isEmpty()) break;
            String[] headerData = line.split(":");
            if(headerData[0].equals("Content-Type")){
                contentType = headerData[1].trim();
            }
            logger.debug(line);
        }
        StringBuilder sb = new StringBuilder();
        while(br.ready()){
            sb.append((char)br.read());
        }
        RequestBody requestBody = new RequestBody(contentType,sb.toString());

        System.out.println(sb.toString());
        return new HttpRequest(fl.httpVersion(), fl.method(), fl.url(), requestBody.makeBytes(), requestBody.getContentType());
    }

    private static FirstLine getFirstLine(String firstLine) {
        String[] firstLineData = firstLine.split(" ");
        String method = firstLineData[0];
        String url = firstLineData[1];
        String httpVersion = firstLineData[2];
        return new FirstLine(method, url, httpVersion);
    }

    private record FirstLine(String method, String url, String httpVersion) {
    }
}
