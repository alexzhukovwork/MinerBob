package com.mygdx.game.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.helpers.AssetLoader;

/**
 * Created by Алексей on 15.09.2017.
 */

public class StoneBlock implements ITypeBlock {
    private float level;
    private Color color;

    public StoneBlock(float level) {
        this.level = level;
        color = new Color(1, 1, 0, 1);
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int countKick) {
        return AssetLoader.stoneBlock;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setLevel(float level) {
        this.level = level;
    }

    @Override
    public int getScore() {
        return 3;
    }
}
