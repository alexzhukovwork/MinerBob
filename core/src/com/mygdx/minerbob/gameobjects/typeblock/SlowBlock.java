package com.mygdx.minerbob.gameobjects.typeblock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 10.01.2018.
 */

public class SlowBlock implements ITypeBlock {
    private float level;
    private Color color;
    private AssetLoader assetLoader;

    public SlowBlock(AssetLoader assetLoader, float level) {
        this.level = level;
        color = new Color(1, 1, 0, 1);
        this.assetLoader = assetLoader;
    }

    @Override
    public float getLevel() {
        return level;
    }

    @Override
    public TextureRegion getTexture(int count, int kick) {
        return assetLoader.yesMenuTexture;
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
        return "Slow";
    }
}
