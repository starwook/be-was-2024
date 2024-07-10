package webserver.back.data;

import webserver.back.Error.WrongDataFormatException;

import java.util.Map;

public class SignInForm {

    private String name;
    private String password;
    private String userId;
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public SignInForm(Map<String,String> informations) throws WrongDataFormatException {
        if(informations==null) throw new WrongDataFormatException();
        this.name = informations.get("name");
        this.password = informations.get("password");
        this.userId = informations.get("userId");
        CheckVariablesNotNull();
    }
    public void CheckVariablesNotNull() throws WrongDataFormatException{
        if(name==null||password==null||userId==null) throw new WrongDataFormatException("data should not be null");
    }
}
