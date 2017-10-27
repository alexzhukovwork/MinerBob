package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import java.util.Date;

/**
 * Created by Алексей on 10.09.2017.
 */

public class AssetLoader {
    public static Animation actorAnimation;

    public static Texture texture;
    public static Texture textureBlock;
    public static Texture textureBg;
    public static Texture textureStart;
    public static Texture textureButtons;
    public static Texture textureSplashScreen;

    public static BitmapFont font;


    public static TextureRegion actorKick1, actorKick2, actorKick3, actorFall;
    public static TextureRegion startField;
    public static TextureRegion bgFirst;
    public static TextureRegion currentTexture;
    public static TextureRegion startScreen;
    public static TextureRegion splashScreen;

    //buttons
    public static TextureRegion buttonPlay;
    public static TextureRegion buttonPlayClicked;
    public static TextureRegion buttonPause;
    public static TextureRegion buttonPauseClicked;
    public static TextureRegion buttonClose;
    public static TextureRegion buttonCloseClicked;
    public static TextureRegion buttonBack;
    public static TextureRegion buttonBackClicked;
    public static TextureRegion buttonLeft;
    public static TextureRegion buttonLeftClicked;
    public static TextureRegion buttonRight;
    public static TextureRegion buttonRightClicked;
    public static TextureRegion buttonShop;
    public static TextureRegion buttonShopClicked;
    public static TextureRegion buttonOk;
    public static TextureRegion buttonOkClicked;
    public static TextureRegion buttonSound;

    private static Texture testPlay;
    private static Texture testShop;
    private static Texture testSound;

    public static TextureRegion earthBlock;


    public static Animation currentAnimation;

    public static Array<ActorTexture> textures;
    public static Array<TextureRegion> earthTextures;

    public static Preferences prefs;
    public static boolean isInternet;

    public static void loadSplashScreen() {
        textureSplashScreen = new Texture(Gdx.files.internal("img/splashScreen.jpg"));
        splashScreen = new TextureRegion(textureSplashScreen, 0, 0, 640, 640);
        splashScreen.flip(false, true);
    }

    private static void initButtons() {
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

    public static void load() {
        earthTextures = new Array<TextureRegion>();
      /*  for (int i = 1; i < 2; i++) {
            textureBlock = new Texture(Gdx.files.internal("img/" + i + ".png"));
            earthBlock = new TextureRegion(textureBlock, 0, 0, 64, 59);
            earthBlock.flip(false, true);
            earthTextures.add(earthBlock);
        }*/
        textureBlock = new Texture(Gdx.files.internal("img/" + 1 + ".png"));
        earthBlock = new TextureRegion(textureBlock, 0, 0, 64, 59);
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

    private static void initPrefs() {
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

        prefs.flush();
    }

    private static void checkInternet() {
        MyDate myDate = new MyDate();
        Date date = myDate.getDate();
        Date lastDate = new Date(AssetLoader.prefs.getLong("dateVideo"));
        int countBonus = prefs.getInteger("countBonus");

        isInternet = date != null;

        if (isInternet) {
            if (!date.after(lastDate) && AssetLoader.prefs.getInteger("countVideo") >= 3)
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

    private static void initMoney() {
        if (!prefs.contains("money")) {
            prefs.putInteger("money", 0);
        } else {
            Money.money = prefs.getInteger("money");
        }
    }

    private static void initRecord() {
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    private static void initBought() {
        for (int i = 0; i < textures.size; i++) {
            if (!prefs.contains("bought" + i)) {
                if (i == 0)
                    prefs.putBoolean("bought" + i, true);
                else
                    prefs.putBoolean("bought" + i, false);
            }
        }
    }

    private static void initSelected() {
        for (int i = 0; i < textures.size; i++) {
            if (!prefs.contains("selected" + i)) {
                if (i == 0)
                    prefs.putBoolean("selected" + i, true);
                else
                    prefs.putBoolean("selected" + i, false);
            }
        }
    }

    public static void dispose() {
        textureStart.dispose();
        textureBlock.dispose();
        textureBg.dispose();
        texture.dispose();
//        textureStartScreen.dispose();
        font.dispose();
    }
}
