package com.readkite.entities;

/**
 * Created by Vadym on 20.03.2016.
 */
public class UserEntity {
    private String login;
    private String password;

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserEntity() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
