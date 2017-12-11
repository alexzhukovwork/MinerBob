package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by vovan on 11.12.2017.
 */

public class Record {

    private static AssetLoader assetLoader;

    public Record(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

    public static void draw(SpriteBatch batcher, float x, float y, int score) {
        float width = TextSize.getHeight(assetLoader.font, score + "");
        batcher.begin();
        batcher.draw(assetLoader.recordTexture, x, y + 1f, width, width);
        assetLoader.font.draw(batcher, "" + score, x + width + 1f, y);
        batcher.end();
    }
}
