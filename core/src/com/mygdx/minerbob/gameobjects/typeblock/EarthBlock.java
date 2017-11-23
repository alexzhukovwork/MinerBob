package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 13.09.2017.
 */

public class EarthBlock implements ITypeBlock {
    private float level;
    private Color color;
    private AssetLoader assetLoader;

    public EarthBlock(AssetLoader assetLoader, float level) {
        color = new Color(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        this.level = level;
        this.assetLoader = assetLoader;
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int count,int kick) {
        return assetLoader.earthTextures.get(count).get(kick);
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
