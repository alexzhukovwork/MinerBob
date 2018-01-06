package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import java.util.Date;

import javax.xml.soap.Text;

/**
 * Created by Алексей on 10.09.2017.
 */

public class AssetLoader {
    public Animation actorAnimation;

    private TextureAtlas atlasTexture;
    private TextureAtlas atlasTextureField;
    private TextureAtlas atlasTextureActor;
    public Texture textureSplashScreen;

    public BitmapFont font;

    public TextureRegion actorKick1, actorKick2, actorKick3, actorFall;
    public TextureRegion startField;
    public TextureRegion bgBlue, bgBlueToGreen;
    public TextureRegion bgGreen, bgGreenToBlue;
    public TextureRegion currentTexture;
    public TextureRegion splashScreen;
    public TextureRegion starTexture;

    //buttons
    public TextureRegion buttonPlay;
    public TextureRegion buttonPause;
    public TextureRegion buttonShop;
    public TextureRegion buttonSound;
    public TextureRegion buttonStudy;

    public TextureRegion buttonNoneSound;
    public TextureRegion buttonLeft;
    public TextureRegion buttonRight;
    public TextureRegion buttonBack;
    public TextureRegion buttonVideo;

    // Form menu
    public TextureRegion pauseTexture;
    public TextureRegion bestTexture;
    public TextureRegion scoreTexture;
    public TextureRegion boxTexture;
    public TextureRegion menuTexture;
    public TextureRegion videoMenuTexure;
    public TextureRegion playMenuTexture;
    public TextureRegion okMenuTexture;
    public TextureRegion yesMenuTexture;
    public TextureRegion noMenuTexture;
    public TextureRegion buttonPauseTexture;

    //Sounds
    public Sound money;
    public Sound moneycombox2;
    public Sound moneycombox3;
    public Sound moneycombox5;
    public Sound fall;
    public Sound drill;

    public Music bgMusic;

    public TextureRegion tempRegion;


    public Animation currentAnimation;

    public Array<ActorTexture> textures;

    public Array<Array<TextureRegion>> earthTextures;
    public Array<Array<TextureRegion>> grassTextures;
    public Array<Array<TextureRegion>> clayTextures;
    public Array<TextureRegion> deadTextures;
    public Array<Array<TextureRegion>> goldTextures;
    public Array<Array<TextureRegion>> stoneTextures;
    public Array<Array<TextureRegion>> diamondTextures;


    public TextureRegion item;
    public TextureRegion boughtItem;
    public TextureRegion selectedItem;
    public TextureRegion shopField;
    public TextureRegion moneyTexture;
    public TextureRegion recordTexture;

    public TextureRegion [] lava;
    public Array<TextureRegion> restoreTextures;
    public Animation restoreAnimation;
    public Animation lavaAnimation;

    // Combo
    public TextureRegion x2Texture;
    public TextureRegion x3Texture;
    public TextureRegion x5Texture;


    public static Preferences prefs;
    public boolean isInternet;

    public void loadSplashScreen() {
        textureSplashScreen = new Texture(Gdx.files.internal("img/splashScreen.png"));
        splashScreen = new TextureRegion(textureSplashScreen, 0, 0, 1000, 1600);
        splashScreen.flip(false, true);
    }

    private void initFormMenu() {
        scoreTexture = atlasTextureField.findRegion("scoreMenu");
        scoreTexture.flip(false, true);
        pauseTexture = atlasTextureField.findRegion("pauseMenu");
        pauseTexture.flip(false, true);
        bestTexture = atlasTextureField.findRegion("bestMenu");
        bestTexture.flip(false, true);
        boxTexture = atlasTextureField.findRegion("boxMenu");
        boxTexture.flip(false, true);
        menuTexture = atlasTextureField.findRegion("menuMenu");
        menuTexture.flip(false, true);
        videoMenuTexure = atlasTextureField.findRegion("videoMenu");
        videoMenuTexure.flip(false, true);
        playMenuTexture = atlasTextureField.findRegion("playMenu");
        playMenuTexture.flip(false, true);
        okMenuTexture = atlasTextureField.findRegion("okMenu");
        okMenuTexture.flip(false, true);
        yesMenuTexture = atlasTexture.findRegion("yes");
        yesMenuTexture.flip(false, true);
        noMenuTexture = atlasTexture.findRegion("no");
        noMenuTexture.flip(false, true);
        buttonPauseTexture = atlasTexture.findRegion("pause");
        buttonPauseTexture.flip(false, true);
    }

    private void initButtons() {
        buttonPlay = atlasTexture.findRegion("play");
        buttonShop = atlasTexture.findRegion("shop");
        buttonSound = atlasTexture.findRegion("audio");
        buttonNoneSound = atlasTexture.findRegion("noneAudio");
        buttonLeft = atlasTexture.findRegion("left");
        buttonRight = atlasTexture.findRegion("right");
        buttonVideo = atlasTexture.findRegion("video");
        buttonBack = atlasTexture.findRegion("back");
        buttonStudy = atlasTexture.findRegion("studyButton");

        buttonStudy.flip(false, true);
        buttonSound.flip(false, true);
        buttonShop.flip(false, true);
        buttonPlay.flip(false, true);
        buttonNoneSound.flip(false, true);
        buttonLeft.flip(false, true);
        buttonRight.flip(false, true);
        buttonVideo.flip(false, true);
        buttonBack.flip(false, true);
    }

    private void initCombo() {
        x2Texture = atlasTexture.findRegion("2x");
        x2Texture.flip(false, true);
        x3Texture = atlasTexture.findRegion("3x");
        x3Texture.flip(false, true);
        x5Texture = atlasTexture.findRegion("5x");
        x5Texture.flip(false, true);
    }

    private void initLava() {
        lava = new TextureRegion[10];
        for (int i = 0; i < 10; i++)      {
            lava[i] = atlasTexture.findRegion("lava" + i);
            lava[i].flip(false , true);
        }

        lavaAnimation = new Animation(0.1f, lava);
        lavaAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void initBlocks() {
        earthTextures = new Array<Array<TextureRegion>>();
        grassTextures = new Array<Array<TextureRegion>>();
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
        //load sounds
        money = Gdx.audio.newSound(Gdx.files.internal("audio/money.wav"));
        moneycombox2 = Gdx.audio.newSound(Gdx.files.internal("audio/moneycombox2.wav"));
        moneycombox3 = Gdx.audio.newSound(Gdx.files.internal("audio/moneycombox3.wav"));
        moneycombox5 = Gdx.audio.newSound(Gdx.files.internal("audio/moneycombox5.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("audio/fall.wav"));
        drill = Gdx.audio.newSound(Gdx.files.internal("audio/drill.wav"));
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/bgSound.mp3"));

        atlasTexture = new TextureAtlas("Miner Bob.atlas");
        atlasTextureField = new TextureAtlas("Miner Bob Field.atlas");
        initActors();
        initBlocks();
        initLava();
        initFormMenu();
        initCombo();
        moneyTexture = atlasTexture.findRegion("money");
        moneyTexture.flip(false, true);
        recordTexture = atlasTexture.findRegion("starRecord");
        recordTexture.flip(false, true);

        shopField = atlasTextureField.findRegion("Miner-Bob6");
        selectedItem = atlasTexture.findRegion("boughtItem");
        item = atlasTexture.findRegion("item");
        boughtItem = atlasTexture.findRegion("selectedItem");
        selectedItem.flip(false, true);
        boughtItem.flip(false, true);
        item.flip(false, true);
        starTexture = atlasTexture.findRegion("star");
        starTexture.flip(false, true);

        TextureRegion temp;
        restoreTextures = new Array<TextureRegion>();
        for(int i = 0; i < 4; i++) {
            temp = atlasTextureActor.findRegion("Restore" + i);
            temp.flip(false, true);
            restoreTextures.add(temp);
        }
        restoreAnimation = new Animation(0.3f, restoreTextures);
        restoreAnimation.setPlayMode(Animation.PlayMode.LOOP);

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

        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.setUseIntegerPositions(false);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.1f, -0.1f);

    //    font.getData().setScale(0.05f, -0.05f);

        currentAnimation = textures.get(0).animation;
        currentTexture = textures.get(0).textureFall;

        initPrefs();
        checkInternet();
    }

    private void initActors() {
        atlasTextureActor = new TextureAtlas("Miner Bob Actor.atlas");
        textures = new Array<ActorTexture>();
        initCurrentActor("actor", 0);
        initCurrentActor("hohol", 100);
        initCurrentActor("pirate", 200);
        initCurrentActor("rastaman", 500);
        initCurrentActor("cosmonaut", 750);
        initCurrentActor("santa", 1000);
    }

    private void initCurrentActor(String actor, int cost) {
        actorFall = atlasTextureActor.findRegion(actor + "0");
        actorFall.flip(false, true);

        actorKick1 = atlasTextureActor.findRegion(actor + "1");
        actorKick1.flip(false, true);

        actorKick2 = atlasTextureActor.findRegion(actor + "2");
        actorKick2.flip(false, true);

        actorKick3 = atlasTextureActor.findRegion(actor + "3");
        actorKick3.flip(false, true);

        TextureRegion[] actors = { actorKick1, actorKick2, actorKick3 };
        actorAnimation = new Animation(0.09f, actors);
        actorAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        textures.add(new ActorTexture(actorFall, actorAnimation, cost));
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

        if(!prefs.contains("isFirstRun")) {
            prefs.putBoolean("isFirstRun", true);
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
        money.dispose();
        moneycombox2.dispose();
        moneycombox3.dispose();
        moneycombox5.dispose();
        fall.dispose();
        drill.dispose();
        bgMusic.dispose();
    }
}
