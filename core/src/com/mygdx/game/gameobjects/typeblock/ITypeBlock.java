package com.mygdx.game.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 13.09.2017.
 */

public interface ITypeBlock {
    public float getLevel();
    public TextureRegion getTexture(int countKick);
    public Color getColor();
    public void setLevel(float level);
    public int getScore();
}
