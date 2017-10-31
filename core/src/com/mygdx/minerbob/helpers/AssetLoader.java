package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import java.util.Date;

/**
 * Created by Алексей on 10.09.2017.
 */

public class AssetLoader {
    public Animation actorAnimation;

    public Texture texture;
    public Texture textureBlock;
    public Texture textureBg;
    public Texture textureStart;
    public Texture textureButtons;
    public Texture textureSplashScreen;

    public BitmapFont font;

    public TextureRegion actorKick1, actorKick2, actorKick3, actorFall;
    public TextureRegion startField;
    public TextureRegion bgFirst;
    public TextureRegion currentTexture;
    public TextureRegion startScreen;
    public TextureRegion splashScreen;

    //buttons
    public TextureRegion buttonPlay;
    public TextureRegion buttonPlayClicked;
    public TextureRegion buttonPause;
    public TextureRegion buttonPauseClicked;
    public TextureRegion buttonClose;
    public TextureRegion buttonCloseClicked;
    public TextureRegion buttonBack;
    public TextureRegion buttonBackClicked;
    public TextureRegion buttonLeft;
    public TextureRegion buttonLeftClicked;
    public TextureRegion buttonRight;
    public TextureRegion buttonRightClicked;
    public TextureRegion buttonShop;
    public TextureRegion buttonShopClicked;
    public TextureRegion buttonOk;
    public TextureRegion buttonOkClicked;
    public TextureRegion buttonSound;

    private Texture testPlay;
    private Texture testShop;
    private Texture testSound;

    public TextureRegion earthBlock;


    public Animation currentAnimation;

    public Array<ActorTexture> textures;
    public Array<TextureRegion> earthTextures;

    public Array<TextureRegion> restoreTextures;
    public Animation restoreAnimation;

    public static Preferences prefs;
    public boolean isInternet;

    public void loadSplashScreen() {
        textureSplashScreen = new Texture(Gdx.files.internal("img/splashScreen.jpg"));
        splashScreen = new TextureRegion(textureSplashScreen, 0, 0, 640, 640);
        splashScreen.flip(false, true);
    }

    private void initButtons() {
        textureButtons = new Texture(Gdx.files.internal("img/buttons.png"));
        buttonPlay = new TextureRegion(textureButtons, 512 * 3 + 3, 512 + 1, 512, 512);
        buttonPlayClicked = new TextureRegion(textureButtons, 512 * 4 + 4, 0, 512, 512);
        buttonPause = new TextureRegion(textureButtons, 512 * 3 + 3, 0, 512, 512);
        buttonPauseClicked = new TextureRegion(textureButtons, 512 * 2 + 2, 512 * 2 + 2, 512, 512);
        buttonClose = new TextureRegion(textureButtons, 512 + 1, 0, 512, 512);
        buttonCloseClicked = new TextureRegion(textureButtons, 0, 512 * 2 + 2, 512, 512);
        buttonBack = new TextureRegion(textureButtons, 0, 0, 512, 512);
        buttonBackClicked = new TextureRegion(textureButtons, 0, 512 + 1, 512, 512);
        buttonLeft = new TextureRegion(textureButtons, 512 + 1, 512 + 1, 512, 512);
        buttonLeftClicked = new TextureRegion(textureButtons, 512 * 2 + 2, 0, 512, 512);
        buttonRight = new TextureRegion(textureButtons, 512 * 3 + 3, 512 * 2 + 2, 512, 512);
        buttonRightClicked = new TextureRegion(textureButtons, 512 * 4 + 4, 512 + 1, 512, 512);
        buttonShop = new TextureRegion(textureButtons, 512 * 5 + 5, 0, 512, 512);
        buttonShopClicked = new TextureRegion(textureButtons, 512 * 4 + 4, 512 * 2 + 2, 512, 512);
        buttonOk = new TextureRegion(textureButtons, 512 + 1, 512 * 2 + 2, 512, 512);
        buttonOkClicked = new TextureRegion(textureButtons, 512 * 2 + 2, 512 + 1, 512, 512);

        testPlay = new Texture(Gdx.files.internal("img/play.png"));
        buttonPlay = new TextureRegion(testPlay, 0, 0, 257, 269);

        testShop = new Texture(Gdx.files.internal("img/shop.png"));
        buttonShop = new TextureRegion(testShop, 0, 0, 129, 129);

        testSound = new Texture(Gdx.files.internal("img/audio.png"));
        buttonSound = new TextureRegion(testSound, 0, 0, 129, 129);


        buttonSound.flip(false, true);
        buttonOk.flip(false, true);
        buttonOkClicked.flip(false, true);
        buttonShop.flip(false, true);
        buttonShopClicked.flip(false, true);
        buttonRight.flip(false, true);
        buttonRightClicked.flip(false, true);
        buttonLeft.flip(false, true);
        buttonLeftClicked.flip(false, true);
        buttonBack.flip(false, true);
        buttonBackClicked.flip(false, true);
        buttonClose.flip(false, true);
        buttonCloseClicked.flip(false, true);
        buttonPause.flip(false, true);
        buttonPauseClicked.flip(false, true);
        buttonPlay.flip(false, true);
        buttonPlayClicked.flip(false, true);
    }

    public void load() {

        restoreTextures = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++) {
            TextureRegion temp = new TextureRegion(new Texture(Gdx.files.internal("img/Restore" + i + ".png")));
            temp.flip(false, true);
            restoreTextures.add(temp);
        }
        restoreAnimation = new Animation(0.3f, restoreTextures);
        restoreAnimation.setPlayMode(Animation.PlayMode.LOOP);

        earthTextures = new Array<TextureRegion>();
        for (int i = 0; i < 10; i++) {
            textureBlock = new Texture(Gdx.files.internal("img/earth" + i + ".png"));
            earthBlock = new TextureRegion(textureBlock, 0, 0, 201, 101);
            earthBlock.flip(false, true);
            earthTextures.add(earthBlock);
        }
        //textureBlock = new Texture(Gdx.files.internal("img/" + 1 + ".png"));
        //earthBlock = new TextureRegion(textureBlock, 0, 0, 64, 59);
        textures = new Array<ActorTexture>();
        initButtons();
        textureBg = new Texture(Gdx.files.internal("img/BG.png"));
        textureBg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bgFirst = new TextureRegion(textureBg, 500, 800);
        textureBlock = new Texture(Gdx.files.internal("img/Blocks.png"));
        textureBlock.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        texture = new Texture(Gdx.files.internal("img/Actor.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        textureStart = new Texture(Gdx.files.internal("img/StartField.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        startField = new TextureRegion(textureStart, 0, 0, 256, 512);
        startField.flip(false, true);

        actorFall = new TextureRegion(texture, 0, 11, 259, 370);
        actorFall.flip(false, true);

        actorKick1 = new TextureRegion(texture, 323, 11, 259, 370);
        actorKick1.flip(false, true);

        actorKick2 = new TextureRegion(texture, 656, 11, 259, 370);
        actorKick2.flip(false, true);

        actorKick3 = new TextureRegion(texture, 987, 11, 259, 370);
        actorKick3.flip(false, true);

        TextureRegion[] actors = { actorKick1, actorKick2, actorKick3 };
        actorAnimation = new Animation(0.09f, actors);
        actorAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));
        textures.add(new ActorTexture(actorFall, actorAnimation, 10));


        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.05f, -0.05f);

        currentAnimation = textures.get(0).animation;
        currentTexture = textures.get(0).textureFall;

        initPrefs();
        checkInternet();
    }

    private void initPrefs() {
        prefs = Gdx.app.getPreferences("MinerBob");

        initRecord();
        initBought();
        initSelected();
        initMoney();

        prefs.flush();

        for (int i = 0; i < textures.size; i++) {
            if (prefs.getBoolean("selected" + i)) {
                currentTexture = textures.get(i).textureFall;
                currentAnimation = textures.get(i).animation;
                i = textures.size;
            }
        }

        if (!prefs.contains("dateVideo")) {
            prefs.putLong("dateVideo", 0);
        }

        if (!prefs.contains("countVideo")) {
            prefs.putInteger("countVideo", 0);
        }

        if (!prefs.contains("isNextDay")) {
            prefs.putBoolean("isNextDay", false);
        }

        if (!prefs.contains("countBonus")) {
            prefs.putInteger("countBonus", 1);
        }

        if(!prefs.contains("countRestore")) {
            prefs.putInteger("countRestore", 0);
        }

        prefs.flush();
    }

    private void checkInternet() {
        MyDate myDate = new MyDate();
        Date date = myDate.getDate();
        Date lastDate = new Date(prefs.getLong("dateVideo"));
        int countBonus = prefs.getInteger("countBonus");

        isInternet = date != null;

        if (isInternet) {
            if (!date.after(lastDate) && prefs.getInteger("countVideo") >= 3)
                isInternet = false;
            else if (date.after(lastDate)) {

                if (myDate.isNextDay(lastDate.getTime(), date.getTime()) && countBonus < 5) {
                    prefs.putInteger("countBonus", countBonus + 1);
                } else {
                    prefs.putInteger("countBonus", 1);
                }

                prefs.putBoolean("isNextDay", true);

                prefs.putInteger("countVideo", 0);
                prefs.putLong("dateVideo", date.getTime());
            } else
                prefs.putBoolean("isNextDay", false);
        }

        prefs.flush();
    }

    private void initMoney() {
        if (!prefs.contains("money")) {
            prefs.putInteger("money", 0);
        } else {
            Money.money = prefs.getInteger("money");
        }
    }

    private void initRecord() {
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    private void initBought() {
        for (int i = 0; i < textures.size; i++) {
            if (!prefs.contains("bought" + i)) {
                if (i == 0)
                    prefs.putBoolean("bought" + i, true);
                else
                    prefs.putBoolean("bought" + i, false);
            }
        }
    }

    private void initSelected() {
        for (int i = 0; i < textures.size; i++) {
            if (!prefs.contains("selected" + i)) {
                if (i == 0)
                    prefs.putBoolean("selected" + i, true);
                else
                    prefs.putBoolean("selected" + i, false);
            }
        }
    }

    public void dispose() {
        textureStart.dispose();
        textureBlock.dispose();
        textureBg.dispose();
        texture.dispose();
        textureSplashScreen.dispose();
        textureButtons.dispose();
        font.dispose();
    }
}
