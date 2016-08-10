package com.transpot.rubahapi.transpot.model.data;

/**
 * Created by prasetia on 8/10/2016.
 */
public class LoginData {

    String username;
    String password;

    public LoginData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
