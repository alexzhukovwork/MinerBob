package com.mygdx.minerbob.helpers;

/**
 * Created by Алексей on 20.02.2018.
 */

public class User {
    private String login;

    public User() {

    }

    public boolean isRegistr(AssetLoader assetLoader) {
        return assetLoader.containsId();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
