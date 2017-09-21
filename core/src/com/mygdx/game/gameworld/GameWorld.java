package com.mygdx.game.gameworld;

import com.mygdx.game.gameobjects.Actor;
import com.mygdx.game.gameobjects.Field;
import com.mygdx.game.gameobjects.RowBlock;
import com.mygdx.game.helpers.Sound;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.ui.MenuForm;
import com.mygdx.game.ui.PauseForm;
import com.mygdx.game.ui.RunningForm;
import com.mygdx.game.ui.ShopForm;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameWorld {
    private Actor actor, actorPause;
    private RowBlock rowBlock;
    private Field field;
    private GameState currentState;
    private MenuForm menu;
    private ShopForm shop;
    private Sound sound;
    private PauseForm pauseForm;
    private RunningForm runningForm;

    public enum GameState {
       MENU , RUNNING, PAUSE, RESTART, SHOP
    }

    public static boolean isRecord;
    public static boolean isEnd;
    public static int score = 0;

    public GameWorld() {
        runningForm = new RunningForm();
        pauseForm = new PauseForm();
        sound = new Sound();
        shop = new ShopForm();
        menu = new MenuForm();
        currentState = GameState.MENU;
        isRecord = false;
        isEnd = false;
        actor = new Actor(GameScreen.WIDTH / 5 * 2 + 1, -10, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        actorPause = new Actor(GameScreen.WIDTH / 5 * 2 + 1, -10, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        rowBlock = new RowBlock(this);
        field = new Field();
    }

    public void update(float delta) {
       // Gdx.app.log("GameWorld", "update");
       // Gdx.app.log("FPS", 1 / delta + "");
        if (isRunning()) {
            field.update(delta);
            actor.update(delta);
            rowBlock.update(delta);
        }

        if (isShop()) {
            shop.update(delta);
        }

        if (!actor.getAlive()) {
            stop();
            pauseForm.checkRecord();
        }

    }

    public void stop()
    {
        actor.stop();
        rowBlock.stop();
    }

    public void pause() {
        rowBlock.pause();
        pauseActor();
    }

    public void resume() {
        rowBlock.resume();
        resumeActor();
    }

    public void restart() {
        isRecord = false;
        score = 0;
        actor.restart(GameScreen.WIDTH / 5 * 2 + 1, -10, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        rowBlock.restart();
    }

    public Actor getActor() {
        return actor;
    }

    public RowBlock getRowBlock() {
        return rowBlock;
    }

    public Field getField() {
        return field;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isPause() {
        return currentState == GameState.PAUSE;
    }

    public boolean isRestart() {
        return currentState == GameState.RESTART;
    }

    public boolean isShop() {
        return currentState == GameState.SHOP;
    }

    public MenuForm getMenu() {
        return menu;
    }

    public void setState(GameState state) {
        currentState = state;
    }

    public ShopForm getShop() {
        return shop;
    }

    public Sound getSound() {
        return sound;
    }

    public PauseForm getPauseForm() {
        return pauseForm;
    }

    public RunningForm getRunningForm() {
        return runningForm;
    }

    public void pauseActor() {
        actorPause.setAcceleration(actor.getAcceleration());
        actorPause.setOnBlock(actor.getOnBlock());
        actorPause.setPosition(actor.getX(), actor.getY());
        actorPause.setVelocity(actor.getVelocity().x, actor.getVelocity().y);
        actorPause.setAlive(actor.getAlive());
    }

    public void resumeActor() {
        actor.setAcceleration(actorPause.getAcceleration());
        actor.setOnBlock(actorPause.getOnBlock());
        actor.setPosition(actorPause.getX(), actorPause.getY());
        actor.setVelocity(actorPause.getVelocity().x, actorPause.getVelocity().y);
        actor.setAlive(actorPause.getAlive());
    }
}
