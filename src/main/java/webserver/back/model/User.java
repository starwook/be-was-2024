package webserver.back.model;

import webserver.back.Error.WrongDataFormatException;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }

    public User(String userId, String password, String name) throws WrongDataFormatException {
        this.userId = userId;
        this.password = password;
        this.name = name;
        isNewUserVariableNull();
    }

    public User(String userId, String password) throws WrongDataFormatException {
        this.userId = userId;
        this.password = password;
        isFindUserVariableNull();
    }

    public static User newUser(Map<String,String> informations) throws WrongDataFormatException {
        if(informations==null) throw new WrongDataFormatException();
        return new User(informations.get("userId"), informations.get("password"),informations.get("name"));
    }
    public void isNewUserVariableNull() throws WrongDataFormatException{
        if(name==null||password==null||userId==null) throw new WrongDataFormatException("required data null");
    }
    public void isFindUserVariableNull() throws WrongDataFormatException{
        if(password==null||userId==null) throw new WrongDataFormatException("required data null");
    }
    public static User findUser(Map<String,String> informations) throws WrongDataFormatException {
        if(informations==null) throw new WrongDataFormatException();
        String userId = informations.get("userId");
        String password = informations.get("password");
        return new User(userId,password);
    }

}
