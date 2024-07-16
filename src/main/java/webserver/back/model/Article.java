package webserver.back.model;

import webserver.back.Error.WrongDataFormatException;

import java.util.Map;

public class Article {
    String articleId;
    String contents;
    String title;
    String userId;


    public Article(String contents, String title, String userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;


    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public static Article newArticle(Map<String,String> informations) throws WrongDataFormatException {
        if(informations==null) throw new WrongDataFormatException();
        return new Article(informations.get("article"),"",null);
    }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "contents='" + contents + '\'' +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }
}
