package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 07.01.2018.
 */

public class LavaBlock implements ITypeBlock {
    private float level;
    private Color color;
    private AssetLoader assetLoader;

    public LavaBlock(AssetLoader assetLoader, float level) {
        color = new Color(255, 0, 0, 1);
        this.level = level;
        this.assetLoader = assetLoader;
    }

    @Override
    public float getLevel() {
        return 10000;
    }

    @Override
    public TextureRegion getTexture(int count, int kick) {
        return assetLoader.lava[0];
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
        return "Lava";
    }
}
