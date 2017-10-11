package com.mygdx.minerbob.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 05.10.2017.
 */

public class StartScreen implements Screen {
    private SpriteBatch batcher;
    private OrthographicCamera camera;

    @Override
    public void show() {
        batcher = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameScreen.WIDTH, GameScreen.HEIGHT);
        batcher.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        batcher.begin();
        batcher.draw(AssetLoader.startScreen, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
        batcher.end();
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
