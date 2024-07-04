package webserver;

import java.io.*;
import java.net.Socket;

import Mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import returnType.ContentTypeMaker;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final ContentTypeMaker contentTypeMaker = new ContentTypeMaker();
    private final RequestParser requestParser;

    private Socket connection;
    private ResponsePrinter responsePrinter;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
        requestParser = new RequestParser();
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            HttpRequest httpRequest = requestParser.parseRequest(in);
            ResponseMaker responseMaker = new ResponseMaker(new UserMapper(),new ResponsePrinter());
            responseMaker.makeResponse(httpRequest,out);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
