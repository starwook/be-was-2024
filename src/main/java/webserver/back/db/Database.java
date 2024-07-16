package webserver.back.db;

import webserver.back.Error.UserNotFoundException;
import webserver.back.model.Article;
import webserver.back.model.User;
import webserver.back.session.Session;

import java.util.*;

public class Database {
    private static Map<String, User> users = new HashMap<>();
    private static List<Session> sessions = new ArrayList<>();
    private static List<Article> articles = new ArrayList<>();
    public static int articleId =0;

    public static Article addArticle(Article article) {
        article.setArticleId(String.valueOf(articleId));
        articleId++;
        articles.add(article);
        return article;
    }

    public static User addUser(User user) {
        users.put(user.getUserId(), user);
        return user;
    }
    public static User loginUser(User user) throws UserNotFoundException {
        String userId = user.getUserId();
        String password = user.getPassword();
        return users.entrySet().stream()
                .filter(e -> userId.equals(e.getKey()))
                .filter(e -> e.getValue().getPassword().equals(password))
                .map(e -> e.getValue())
                .findAny()
                .orElseThrow(UserNotFoundException::new);
    }
    public static String saveSession(Session session) {
        sessions.add(session);
        return session.getSessionId();
    }

    public static List<Article> getArticles() {
        return articles;
    }

    public static List<Session> findAllSessions() {return sessions;}

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static List<User> findAllUsers() {
        return users.values().stream().toList();
    }
}
