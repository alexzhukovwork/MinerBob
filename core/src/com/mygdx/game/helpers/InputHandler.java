package com.mygdx.game.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 11.09.2017.
 */

public class InputHandler implements InputProcessor {
    private float sclX;
    private float sclY;
    private GameWorld gameWorld;
    private Vector2 lastTouch;
    private Vector2 newTouch;
    private Vector2 delta;
    long time = TimeUtils.millis();

    public void setScale() {
        sclY = GameScreen.screenHeight / GameScreen.HEIGHT;
        sclX = GameScreen.screenWidth / GameScreen.WIDTH;
    }

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        setScale();
        lastTouch = new Vector2();
        newTouch = new Vector2();
        delta = new Vector2(0, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float x = scaleX(screenX);
        float y = scaleY(screenY);

        if (gameWorld.isRunning()) {
            if (gameWorld.getRunningForm().isClickedPause(x, y)) {
                gameWorld.setState(GameWorld.GameState.PAUSE);
                gameWorld.pause();
            }
            else
                gameWorld.getActor().onTouch(x, y);

        }

        if (gameWorld.isShop()) {
            if (gameWorld.getShop().isClickedClose(x, y))
                gameWorld.setState(GameWorld.GameState.MENU);
            if (gameWorld.getShop().isClickedBack(x, y)) {
                gameWorld.getShop().setVelocity(GameScreen.WIDTH * 3, 0);
                gameWorld.getShop().setAcceleration(-GameScreen.WIDTH / 10, 0);
                gameWorld.getShop().dicrementPage();
            }
            if (gameWorld.getShop().isClickedNext(x, y)) {
                gameWorld.getShop().setVelocity(-GameScreen.WIDTH * 3, 0);
                gameWorld.getShop().setAcceleration(GameScreen.WIDTH / 10, 0);
                gameWorld.getShop().incrementPage();
            }
            lastTouch.set(screenX, screenY);
            time = TimeUtils.millis();
            return true;
        }

        if (gameWorld.isMenu()) {
            if (gameWorld.getMenu().isClickedPlay(x, y))
                gameWorld.setState(GameWorld.GameState.RUNNING);
            if (gameWorld.getMenu().isClickedShop(x, y)) {
                gameWorld.setState(GameWorld.GameState.SHOP);
            }
            if (gameWorld.getMenu().isClickedSound(x, y));
                gameWorld.getSound().setState();

            return true;

        }

        if (gameWorld.isRestart()) {
            gameWorld.getPauseForm().checkRecord();

            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {
                gameWorld.setState(GameWorld.GameState.MENU);
                gameWorld.restart();
                AssetLoader.prefs.flush();

            }

            if (gameWorld.getPauseForm().isClickedRestart(x, y)) {
                gameWorld.setState(GameWorld.GameState.RUNNING);
                gameWorld.restart();
                AssetLoader.prefs.flush();
            }
        }

        if (gameWorld.isPause()) {
            if (gameWorld.getPauseForm().isClickedResume(x, y)) {
                gameWorld.setState(GameWorld.GameState.RUNNING);
                gameWorld.resume();
            }

            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {
                gameWorld.getPauseForm().checkRecord();
                AssetLoader.prefs.flush();
                gameWorld.restart();
                gameWorld.setState(GameWorld.GameState.MENU);
            }
        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    /*    newTouch.set(screenX, screenY);
        delta = newTouch.cpy().sub(lastTouch);
        delta.set(0, scaleY(delta.y * 50));
        lastTouch.x = newTouch.x;
        lastTouch.y = newTouch.y;
        gameWorld.getShop().setVelocity(delta);*/
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(float screenX) {
        return (int) (screenX / sclX);
    }

    private int scaleY(float screenY) {
        return (int) (screenY / sclY);
    }
}
