package com.mygdx.minerbob.gameworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.minerbob.IRewardVideo;
import com.mygdx.minerbob.gameobjects.Actor;
import com.mygdx.minerbob.gameobjects.Field;
import com.mygdx.minerbob.gameobjects.MoneyAnimation;
import com.mygdx.minerbob.gameobjects.RowBlock;
import com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock;
import com.mygdx.minerbob.helpers.Sound;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.ui.DailyBonus;
import com.mygdx.minerbob.ui.MenuForm;
import com.mygdx.minerbob.ui.PauseForm;
import com.mygdx.minerbob.ui.RunningForm;
import com.mygdx.minerbob.ui.ShopForm;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameWorld {
    private Actor actor;
    private RowBlock rowBlock;
    private Field field;
    private GameState currentState;
    private MenuForm menu;
    private ShopForm shop;
    private Sound sound;
    private PauseForm pauseForm;
    private RunningForm runningForm;
    private DailyBonus dailyBonus;

    public ITypeBlock lastDestroyed = null;
    public int countCombo = 1;
    public int scl = 1;
    public long startCombo = 0;

    public boolean isKickedFirst;
    public boolean isStart;
    public boolean isCollisedSecond;

    public IRewardVideo rewardVideo;

    public MoneyAnimation moneyAnimation;

    public enum GameState {
       MENU , RUNNING, PAUSE, RESTART, SHOP, DAILYBONUS
    }

    public static boolean isRecord;
    public static boolean isEnd;
    public static int score = 0;
    public static int currentMoney = 0;

    public GameWorld(IRewardVideo handler) {
        rewardVideo = handler;
        moneyAnimation = new MoneyAnimation();
        runningForm = new RunningForm();
        pauseForm = new PauseForm();
        sound = new Sound();
        shop = new ShopForm();
        menu = new MenuForm();
        dailyBonus = new DailyBonus();
        currentState = GameState.DAILYBONUS;
        isRecord = false;
        isEnd = false;
        isStart = true;
        isKickedFirst = false;
        actor = new Actor(GameScreen.WIDTH / 5 * 2 + 1, GameScreen.HEIGHT - GameScreen.HEIGHT / 15 - GameScreen.HEIGHT / 7, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7,
                this);
        rowBlock = new RowBlock(this);
        field = new Field(this);
    }

    public void update(float delta) {
       // Gdx.app.log("GameWorld", "update");
      //  Gdx.app.log("FPS", 1 / delta + "");

        if (isRunning()) {
            moneyAnimation.update(delta);
            field.update(delta);
            actor.update(delta);
            rowBlock.update(delta);
        }

        if (isShop()) {
            shop.update(delta);
        }

        if (!actor.getAlive()) {
            stop();
            pauseForm.setState(PauseForm.State.SCORE);
            pauseForm.checkRecord();
        }

    }

    public void stop()
    {
        actor.stop();
        rowBlock.stop();
    }

    public void restart() {
        isCollisedSecond = false;
        isStart = true;
        isKickedFirst = false;
        startCombo = 0;
        countCombo = 0;
        lastDestroyed = null;
        isRecord = false;
        score = 0;
        actor.restart(GameScreen.WIDTH / 5 * 2 + 1, GameScreen.HEIGHT - GameScreen.HEIGHT / 15 - GameScreen.HEIGHT / 7, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        rowBlock.restart();
        field.restart();
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

    public boolean isDailyBonus() {
        return currentState == GameState.DAILYBONUS;
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

    public DailyBonus getDailyBonus() {
        return dailyBonus;
    }
}
