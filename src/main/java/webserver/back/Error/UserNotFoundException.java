package webserver.back.Error;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("user not found");
    }
    public UserNotFoundException(String message){
        super(message);
    }

}
