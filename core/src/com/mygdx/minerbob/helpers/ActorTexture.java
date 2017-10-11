package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 22.09.2017.
 */

public class ActorTexture {
    public TextureRegion textureFall;
    public Animation animation;
    public int cost;

    public ActorTexture(TextureRegion textureFall, Animation animation, int cost) {
        this.textureFall = textureFall;
        this.animation = animation;
        this.cost = cost;
    }
}

