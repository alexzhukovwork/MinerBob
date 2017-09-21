package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.gameworld.GameRenderer;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.helpers.InputHandler;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameScreen implements Screen {
    private float runTime = 0;
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    public static float screenWidth = Gdx.graphics.getWidth();
    public static float screenHeight = Gdx.graphics.getHeight();
    public static float HEIGHT = 136;
    public static float WIDTH = screenWidth / (screenHeight / HEIGHT);

    private InputHandler inputHandler;

    @Override
    public void show() {
        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);
        inputHandler = new InputHandler(gameWorld);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        WIDTH = screenWidth / (screenHeight / HEIGHT);
        inputHandler.setScale();
    }

    @Override
    public void pause() {
        gameWorld.pause();
        Gdx.app.log("Screen", "pause");
    }

    @Override
    public void resume() {
        gameWorld.resume();
        Gdx.app.log("Screen", "resume");
    }

    @Override
    public void hide() {
        Gdx.app.log("Screen", "hide");
    }

    @Override
    public void dispose() {
        Gdx.app.log("Screen", "dispose");
        GameWorld.score = 0;
    }
}
