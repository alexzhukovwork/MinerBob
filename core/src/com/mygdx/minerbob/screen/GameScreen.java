package com.mygdx.minerbob.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.minerbob.IActivityRequestHandler;
import com.mygdx.minerbob.IRewardVideo;
import com.mygdx.minerbob.gameworld.GameRenderer;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.InputHandler;
import com.mygdx.minerbob.ui.PauseForm;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameScreen implements Screen {
    private float runTime = 0;
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private AssetLoader assetLoader;
    private float HEIGHT;
    private float WIDTH;
    private InputHandler inputHandler;
    private IActivityRequestHandler handler;

    public GameScreen(IActivityRequestHandler handler, AssetLoader assetLoader, float WIDTH, float HEIGHT) {
        this.handler = handler;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.assetLoader = assetLoader;
        this.assetLoader.load();
        gameWorld = new GameWorld((IRewardVideo)handler, this.assetLoader, WIDTH, HEIGHT);
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public void show() {
        ((IRewardVideo)handler).setGameWorld(gameWorld);
        gameRenderer = new GameRenderer(gameWorld);
        inputHandler = new InputHandler(gameWorld);
        Gdx.input.setInputProcessor(inputHandler);
        Gdx.input.setCatchBackKey(true);

        if (!AssetLoader.prefs.getBoolean("isNextDay"))
           gameWorld.setState(GameWorld.GameState.MENU);
        else
           gameWorld.setState(GameWorld.GameState.DAILYBONUS);
    }

    @Override
    public void render(float delta) {
        if (gameWorld.isPause() || gameWorld.isRestart())
            handler.showAds(true);
        else
            handler.showAds(false);


        runTime += delta;
        gameWorld.update(delta);

        if (gameWorld.isPause())
            gameRenderer.render(0);
        else
            gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        WIDTH =  Gdx.graphics.getWidth() / (Gdx.graphics.getHeight() / HEIGHT);
        inputHandler.setScale();
    }

    @Override
    public void pause() {
       // gameWorld.pause();
        if (gameWorld.isRunning()) {
            gameWorld.setState(GameWorld.GameState.PAUSE);
            gameWorld.getPauseForm().setState(PauseForm.State.PAUSE);
        }
        Gdx.app.log("Screen", "pause");
    }

    @Override
    public void resume() {
     //   gameWorld.resume();
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
