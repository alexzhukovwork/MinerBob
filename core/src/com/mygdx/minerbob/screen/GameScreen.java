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
    public static float screenWidth = Gdx.graphics.getWidth();
    public static float screenHeight = Gdx.graphics.getHeight();
    public static float HEIGHT = 136;
    public static float WIDTH = screenWidth / (screenHeight / HEIGHT);
    private InputHandler inputHandler;
    private IActivityRequestHandler handler;

    public GameScreen(IActivityRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void show() {
        gameWorld = new com.mygdx.minerbob.gameworld.GameWorld((IRewardVideo)handler);
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
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        WIDTH = screenWidth / (screenHeight / HEIGHT);
        inputHandler.setScale();
    }

    @Override
    public void pause() {
       // gameWorld.pause();
        gameWorld.setState(GameWorld.GameState.PAUSE);
        gameWorld.getPauseForm().setState(PauseForm.State.PAUSE);
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
