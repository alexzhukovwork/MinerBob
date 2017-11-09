package com.mygdx.minerbob.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.MainGame;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by Алексей on 05.10.2017.
 */

public class SplashScreen implements Screen {
    private SpriteBatch batcher;
    private OrthographicCamera camera;
    private final MainGame mainGame;
    private AssetLoader assetLoader;
    private GameScreen gameScreen;
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    private float HEIGHT = 136;
    private float WIDTH = screenWidth / (screenHeight / HEIGHT);

    public SplashScreen(final MainGame mainGame) {
        this.mainGame = mainGame;
        assetLoader = new AssetLoader();
    }

    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    @Override
    public void show() {
        batcher = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, WIDTH, HEIGHT);
        batcher.setProjectionMatrix(camera.combined);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        gameScreen = new GameScreen(mainGame.getHandler(), assetLoader, WIDTH, HEIGHT);
                        TextSize.load();
                      //  assetLoader.font = mainGame.getHandler().generateFont();
                        mainGame.setScreen(gameScreen);
                    }
                });
            }
        }).start();
    }

    @Override
    public void render(float delta) {
        batcher.begin();
        batcher.draw(assetLoader.splashScreen, 0, 0, WIDTH, HEIGHT);
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
