package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 13.09.2017.
 */

public class TitanBlock implements ITypeBlock {
    private float level;
    private Color color;

    public TitanBlock(float level) {
        color = new Color(0, 0, 0, 1);
        this.level = level;
    }

    @Override
    public float getLevel() {
        return 10000;
    }

    @Override
    public TextureRegion getTexture(int countKick) {
        return com.mygdx.minerbob.helpers.AssetLoader.titanBlock;
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
        return 1000;
    }

    @Override
    public String getName() {
        return "Titan";
    }
}
