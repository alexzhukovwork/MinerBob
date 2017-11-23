package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 27.09.2017.
 */

public class GoldBlock implements ITypeBlock {
    private Color color;
    private float level;
    private AssetLoader assetLoader;

    public GoldBlock(AssetLoader assetLoader, float level) {
        color = new Color(0, 0, 1, 1);
        this.level = level;
        this.assetLoader = assetLoader;
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int count,int kick) {
        return assetLoader.goldTextures.get(count).get(kick);
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
        return 0;
    }

    @Override
    public String getName() {
        return "Gold";
    }
}
