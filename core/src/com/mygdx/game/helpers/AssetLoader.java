package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Алексей on 10.09.2017.
 */

public class AssetLoader {
    public static Animation actorAnimation;

    public static Texture texture;

    public static TextureRegion actorKick1, actorKick2, actorKick3, actorFall;

    public static void load() {
        texture = new Texture(Gdx.files.internal("Actor.png"));
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
    }

    public static void dispose() {
        texture.dispose();
    }
}
