package com.mygdx.minerbob.ui.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.Internet.PostRequest;

/**
 * Created by Алексей on 19.02.2018.
 */

public class MyTextInputListener implements Input.TextInputListener {
    private GameWorld gameWorld;
    public static final String LOGIN_NOT_AVAILABLE = "Login isn't available";
    public static final String LOGIN_PLEASE_ENTER = "Please enter login";
    public static final String LOGIN_LONG = "Max length of Login is 6 symbols";

    public MyTextInputListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void input (String text) {
        text = text.replace(" ", "");
        System.out.println("time");
        if (text.equals(""))
            repeat(LOGIN_PLEASE_ENTER);
        else if (text.length() > 6)
            repeat(LOGIN_LONG);
        else
            PostRequest.executeInsert(text, gameWorld, gameWorld.assetLoader.getScore());

    }

    @Override
    public void canceled () {
        repeat(LOGIN_PLEASE_ENTER);
    }

    private void repeat(String string) {
        MyTextInputListener listener = new MyTextInputListener(gameWorld);
        Gdx.input.getTextInput(listener, string, "", "Login");
    }
}