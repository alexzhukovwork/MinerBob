package com.mygdx.minerbob.gameworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.minerbob.IRewardVideo;
import com.mygdx.minerbob.gameobjects.Actor;
import com.mygdx.minerbob.gameobjects.Avalanche;
import com.mygdx.minerbob.gameobjects.Field;
import com.mygdx.minerbob.gameobjects.MoneyAnimation;
import com.mygdx.minerbob.gameobjects.RowBlock;
import com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
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

    private AdId adState;
    public AssetLoader assetLoader;

    public final float buttonSize = GameScreen.WIDTH / 7;

    public enum AdId { SHOPAD, RESTOREAD }

    public ITypeBlock lastDestroyed = null;
    public int countCombo = 1;
    public int scl = 1;
    public long startCombo = 0;

    public boolean isKickedFirst;
    public boolean isStart;
    public boolean isCollisedSecond;
    public boolean isRestoring;

    public IRewardVideo rewardVideo;

    public MoneyAnimation moneyAnimation;
    public Avalanche avalanche;

    public enum GameState {
       MENU , RUNNING, PAUSE, RESTART, SHOP, DAILYBONUS
    }

    public static boolean isRecord;
    public static boolean isEnd;
    public static int score = 0;
    public static int currentMoney = 0;

    public GameWorld(IRewardVideo handler, AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
        new Money(this.assetLoader); //это епта фича
        rewardVideo = handler;
        moneyAnimation = new MoneyAnimation();
        runningForm = new RunningForm(this);
        pauseForm = new PauseForm(this);
        sound = new Sound();
        shop = new ShopForm(this);
        menu = new MenuForm(this);
        avalanche = new Avalanche(this);
        dailyBonus = new DailyBonus(this);
        currentState = GameState.DAILYBONUS;
        isRecord = false;
        isEnd = false;
        isStart = true;
        isKickedFirst = false;
        actor = new Actor(GameScreen.WIDTH / 5 * 2 + 1, GameScreen.HEIGHT - GameScreen.HEIGHT / 15 - GameScreen.HEIGHT / 7, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7,
                this);
        rowBlock = new RowBlock(this);
        field = new Field(this);
        Gdx.app.log("AssetLoader", "world created");
    }

    public void update(float delta) {
         if (isRunning()) {
            moneyAnimation.update(delta);
            field.update(delta);
            actor.update(delta);
            rowBlock.update(delta);
            if (isKickedFirst)
                avalanche.setVelocity(0, 4);
            avalanche.update(delta);
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
        avalanche.restart();
        actor.restart(GameScreen.WIDTH / 5 * 2 + 1, GameScreen.HEIGHT - GameScreen.HEIGHT / 15 - GameScreen.HEIGHT / 7);
        rowBlock.restart();
        field.restart();
    }

    public void restore() {
        isRestoring = true;
        setState(GameWorld.GameState.RUNNING);
        startCombo = 0;
        countCombo = 0;
        lastDestroyed = null;
        isRecord = false;
        int i = rowBlock.identifyRow(0, GameScreen.HEIGHT / 2);
        float x = rowBlock.getBlock(i, 2).getX();
        float y = rowBlock.getBlock(i, 2).getY();
        actor.restore(x + 2, y - GameScreen.HEIGHT / 7);
        rowBlock.setRowEarth(i);
    }

    public void setRestoring() {
        rowBlock.restore();
        actor.setAcceleration(0, 10);
    }

    public boolean isAdShop() {
        return adState == AdId.SHOPAD;
    }

    public boolean isAdRestore() {
        return adState == AdId.RESTOREAD;
    }

    public void setAdState(AdId state) {
        adState = state;
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
