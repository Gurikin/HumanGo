package ru.biv.network;

import javafx.concurrent.Task;
import ru.biv.msgSystem.UserSession;
import ru.biv.utils.TimeHelper;

/**
 * Created by Игорь on 12.01.2017.
 */
public class UserAuth {

    private static final String LOG_URL = "http://localhost:8080/hello";

    private String userName;
    private UserSession requestUserSession;
    private UserSession responseUserSession;

    public UserAuth() {
        requestUserSession = new UserSession();
        responseUserSession = new UserSession();
    }

    public UserSession getUserAuth() {
        requestUserSession.setUserSession(this.userName, null);
        responseUserSession = ObjectRequestSender.sendGetRequest(LOG_URL, requestUserSession);
        return responseUserSession;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
