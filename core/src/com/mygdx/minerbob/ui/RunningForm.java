package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 19.09.2017.
 */

public class RunningForm {
    Rectangle boundsPause;

    TextureRegion pauseTexture;

    public RunningForm() {
        pauseTexture = AssetLoader.buttonPause;
        boundsPause = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, 0, GameScreen.WIDTH / 6, GameScreen.WIDTH / 6);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        batcher.begin();
        batcher.draw(pauseTexture, boundsPause.x, boundsPause.y, boundsPause.width, boundsPause.height);
        batcher.end();
    }

    public boolean isClickedPause(float x, float y) {
        return boundsPause.contains(x, y);
    }
}
