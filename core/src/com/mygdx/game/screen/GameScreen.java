package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.gameworld.GameRenderer;
import com.mygdx.game.gameworld.GameWorld;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameScreen implements Screen {
    private float runTime = 0;
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private static final int screenWidth = Gdx.graphics.getWidth();
    private static final int screenHeight = Gdx.graphics.getHeight();
    public static final int HEIGHT = 136;
    public static final int WIDTH = screenWidth / (screenHeight / HEIGHT);

    @Override
    public void show() {
        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
