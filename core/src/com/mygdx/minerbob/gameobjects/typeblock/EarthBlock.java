package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 13.09.2017.
 */

public class EarthBlock implements ITypeBlock {
    private float level;
    private Color color;

    public EarthBlock(float level) {
        color = new Color(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        this.level = level;
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int kick) {
        return com.mygdx.minerbob.helpers.AssetLoader.earthBlock;
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
        return 1;
    }

    @Override
    public String getName() {
        return "Earth";
    }
}
