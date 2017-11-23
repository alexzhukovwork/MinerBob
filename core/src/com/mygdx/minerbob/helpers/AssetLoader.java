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

    private TextureAtlas atlasTexture;
    private TextureAtlas atlasTextureField;
    public Texture textureSplashScreen;

    public BitmapFont font;

    public TextureRegion actorKick1, actorKick2, actorKick3, actorFall;
    public TextureRegion startField;
    public TextureRegion bgBlue, bgBlueToGreen;
    public TextureRegion bgGreen, bgGreenToBlue;
    public TextureRegion currentTexture;
    public TextureRegion startScreen;
    public TextureRegion splashScreen;
    public TextureRegion starTexture;

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


    public TextureRegion tempRegion;


    public Animation currentAnimation;

    public Array<ActorTexture> textures;

    public Array<Array<TextureRegion>> earthTextures;
    public Array<Array<TextureRegion>> grassTextures;
    public Array<Array<TextureRegion>> clayTextures;
  //  public Array<Array<TextureRegion>> deadTextures;
    public Array<TextureRegion> deadTextures;
    public Array<Array<TextureRegion>> goldTextures;
    public Array<Array<TextureRegion>> stoneTextures;
    public Array<Array<TextureRegion>> diamondTextures;

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

        buttonPlay = atlasTexture.findRegion("play");
        buttonShop = atlasTexture.findRegion("shop");
        buttonSound = atlasTexture.findRegion("audio");

        buttonSound.flip(false, true);
        buttonShop.flip(false, true);
        buttonPlay.flip(false, true);
    }

    private void initBlocks() {
        earthTextures = new Array<Array<TextureRegion>>();
        grassTextures = new Array<Array<TextureRegion>>();
        //deadTextures = new Array<Array<TextureRegion>>();
        deadTextures = new Array<TextureRegion>();
        clayTextures = new Array<Array<TextureRegion>>();
        goldTextures = new Array<Array<TextureRegion>>();
        stoneTextures = new Array<Array<TextureRegion>>();
        diamondTextures = new Array<Array<TextureRegion>>();

        for (int i = 0; i < 5; i ++) {
            earthTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("earth" + i + j);
                tempRegion.flip(false, true);
                earthTextures.get(i).add(tempRegion);
            }
        }

        for (int i = 0; i < 5; i++) {
            grassTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("grass" + i + j);
                tempRegion.flip(false, true);
                grassTextures.get(i).add(tempRegion);
            }
        }

        for (int i = 0; i < 5; i++) {
            clayTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("clay" + i + j);
                tempRegion.flip(false, true);
                clayTextures.get(i).add(tempRegion);
            }
        }

        for (int i = 0; i < 5; i++) {
           // deadTextures.add(new Array<TextureRegion>());
           // for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("black" + i);
                tempRegion.flip(false, true);
               // deadTextures.get(i).add(tempRegion);
                deadTextures.add(tempRegion);
           // }
        }

        for (int i = 0; i < 5; i++) {
            goldTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("money" + i + j);
                tempRegion.flip(false, true);
                goldTextures.get(i).add(tempRegion);
            }
        }

        for (int i = 0; i < 5; i++) {
            stoneTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("dead" + i + j);
                tempRegion.flip(false, true);
                stoneTextures.get(i).add(tempRegion);
            }
        }

        for (int i = 0; i < 5; i++) {
            diamondTextures.add(new Array<TextureRegion>());
            for (int j = 0; j < 11; j++) {
                tempRegion = atlasTexture.findRegion("diamond" + i + j);
                tempRegion.flip(false, true);
                diamondTextures.get(i).add(tempRegion);
            }
        }
    }

    public void load() {
        atlasTexture = new TextureAtlas("Miner Bob.atlas");
        atlasTextureField = new TextureAtlas("Miner Bob Field.atlas");
        initBlocks();

        starTexture = atlasTexture.findRegion("star");
        starTexture.flip(false, true);

        TextureRegion temp;
        restoreTextures = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++) {
            temp = atlasTexture.findRegion("Restore" + i);
            temp.flip(false, true);
            restoreTextures.add(temp);
        }
        restoreAnimation = new Animation(0.3f, restoreTextures);
        restoreAnimation.setPlayMode(Animation.PlayMode.LOOP);

        textures = new Array<ActorTexture>();
        initButtons();

        bgBlue = atlasTextureField.findRegion("Miner-Bob2");
        bgBlue.flip(false, true);
        bgBlueToGreen = atlasTextureField.findRegion("Miner-Bob4");
        bgBlueToGreen.flip(false, true);
        bgGreen = atlasTextureField.findRegion("Miner-Bob3");
        bgGreen.flip(false, true);
        bgGreenToBlue = atlasTextureField.findRegion("Miner-Bob5");
        bgGreenToBlue.flip(false, true);

        startField = atlasTextureField.findRegion("Miner-Bob1");
        startField.flip(false, true);

        actorFall = atlasTexture.findRegion("actor1");
        actorFall.flip(false, true);

        actorKick1 = atlasTexture.findRegion("actor2");
        actorKick1.flip(false, true);

        actorKick2 = atlasTexture.findRegion("actor3");
        actorKick2.flip(false, true);

        actorKick3 = atlasTexture.findRegion("actor4");
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
        textures.add(new ActorTexture(actorFall, actorAnimation, 10000));
        textures.add(new ActorTexture(actorFall, actorAnimation, 1000));


        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(.08f, -.08f);

    //    font.getData().setScale(0.05f, -0.05f);

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
        atlasTexture.dispose();
        atlasTextureField.dispose();
       // textureSplashScreen.dispose();
        font.dispose();
    }
}
