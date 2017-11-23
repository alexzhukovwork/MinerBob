package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 15.09.2017.
 */

public class ClayBlock implements ITypeBlock {
    private float level;
    private Color color;
    private AssetLoader assetLoader;

    public ClayBlock(AssetLoader assetLoader, float level) {
        this.level = level;
        color = new Color(0, 1, 0, 1);
        this.assetLoader = assetLoader;
    }
    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int count,int kick) {
        return assetLoader.clayTextures.get(count).get(kick);
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
        return 2;
    }

    @Override
    public String getName() {
        return "Clay";
    }
}
