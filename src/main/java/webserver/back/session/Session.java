package webserver.back.session;

import java.util.UUID;

public class Session {
    private String sessionId;
    private String userId;


    public Session(String userId){
        this.userId = userId;
        this.sessionId = String.valueOf(UUID.randomUUID());
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

}
