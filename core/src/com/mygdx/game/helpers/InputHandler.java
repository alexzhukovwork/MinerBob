package com.mygdx.game.helpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 11.09.2017.
 */

public class InputHandler implements InputProcessor {
    private float sclX;
    private float sclY;
    private GameWorld gameWorld;

    public void setScale() {
        sclY = GameScreen.screenHeight / GameScreen.HEIGHT;
        sclX = GameScreen.screenWidth / GameScreen.WIDTH;
    }

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        setScale();
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
        if (!GameWorld.isEnd)
            gameWorld.getActor().onTouch(scaleX(screenX), scaleY(screenY));
        else {
            GameWorld.isEnd = false;
            gameWorld.restart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / sclX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / sclY);
    }
}
