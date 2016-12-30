package ru.biv.msgSystem;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Игорь on 27.12.2016.
 */
public class UserSession implements Serializable{
    private Map<String, Integer> user = new HashMap<String, Integer>();

    private enum auth {AUTHORIZATING, AUTH, DONT_AUTH, START}

    ;

    public UserSession() {
        userClear();
    }

    public void setUserSession(String userName, Integer userId) {
        userClear();
        user.put(userName, userId);
    }

    public Map<String, Integer> getUser() {
        return user;
    }

    public Integer getUserId(String userName) {
        return user.get(userName);
    }

    public String getUserName() {
        String userName = "";
        for (Entry<String, Integer> entry : user.entrySet()) {
            userName = entry.getKey();
        }
        return userName;
    }

    public String getAuth() {
        if (this.getUserName() == null && this.user.get(this.getUserName()) == null) {
            return "";
        }
        if (this.user.get(this.getUserName()) == null) {
            return auth.AUTHORIZATING.toString();
        }
        if (this.user.get(this.getUserName()) == 0) {
            return auth.DONT_AUTH.toString();
        }
        if (this.user.get(this.getUserName()) != null) {
            return auth.AUTH.toString();
        } else {
            return "";
        }
    }

    public void userClear() {
        user.clear();
    }
}