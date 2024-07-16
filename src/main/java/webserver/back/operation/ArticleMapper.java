package webserver.back.operation;

import webserver.back.Error.WrongDataFormatException;
import webserver.back.byteReader.ResponseJsonBody;
import webserver.back.data.StatusCode;
import webserver.back.db.Database;
import webserver.back.model.Article;
import webserver.front.data.HttpResponse;

import java.util.Map;

public class ArticleMapper {

    public HttpResponse makeArticle(Map<String,String> bodyVariables) throws WrongDataFormatException {

        Article article = Article.newArticle(bodyVariables);
        Database.addArticle(article);
        ResponseJsonBody body = new ResponseJsonBody(article);
        return HttpResponseMaker.makeHttpResponse(body, StatusCode.FOUND.getMessage(),"/index.html");
    }
}
