package com.mygdx.minerbob.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.MainGame;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by Алексей on 05.10.2017.
 */

public class SplashScreen implements Screen {
    private SpriteBatch batcher;
    private OrthographicCamera camera;
    private final MainGame mainGame;


    public SplashScreen(final MainGame mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void show() {
        batcher = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameScreen.WIDTH, GameScreen.HEIGHT);
        batcher.setProjectionMatrix(camera.combined);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen gameScreen = new GameScreen(mainGame.getHandler());
                        AssetLoader.load();
                        TextSize.load();
                    /*    try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        mainGame.setScreen(gameScreen);
                    }
                });
            }
        }).start();
    }

    @Override
    public void render(float delta) {
        batcher.begin();
        batcher.draw(AssetLoader.splashScreen, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
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
