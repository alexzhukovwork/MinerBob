package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 10.09.2017.
 */

public class AssetLoader {
    public static Animation actorAnimation;

    public static Texture texture;
    public static Texture textureBlock;
    public static Texture textureBg;

    public static BitmapFont font, shadow;

    public static TextureRegion actorKick1, actorKick2, actorKick3, actorFall;
    public static TextureRegion earthBlock, clayBlock, stoneBlock, diamondBlock, titanBlock;
    public static TextureRegion bgFirst;


    public static Preferences prefs;

    public static void load() {
        prefs = Gdx.app.getPreferences("MinerBob");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }


        textureBg = new Texture(Gdx.files.internal("img/BG.png"));
        textureBg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bgFirst = new TextureRegion(textureBg, 500, 800);
        textureBlock = new Texture(Gdx.files.internal("img/Blocks.png"));
        textureBlock.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        earthBlock = new TextureRegion(textureBlock, 0, 0, 100, 66);
        earthBlock.flip(false, true);

        clayBlock = new TextureRegion(textureBlock, 100, 0, 100, 66);
        clayBlock.flip(false, true);

        stoneBlock = new TextureRegion(textureBlock, 200, 0, 100, 66);
        stoneBlock.flip(false, true);

        diamondBlock = new TextureRegion(textureBlock, 300, 0, 100, 66);
        diamondBlock.flip(false, true);

        titanBlock = new TextureRegion(textureBlock, 400, 0, 100, 66);
        titanBlock.flip(false, true);


        texture = new Texture(Gdx.files.internal("img/Actor.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

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

        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f * GameScreen.WIDTH /  GameScreen.screenWidth, -0.5f * GameScreen.HEIGHT / GameScreen.screenHeight);

        //font.set(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shadow.getData().setScale(.25f, -.25f);
        //shadow.setScale(.25f, -.25f);
    }

    public static void dispose() {
        texture.dispose();
    }
}
