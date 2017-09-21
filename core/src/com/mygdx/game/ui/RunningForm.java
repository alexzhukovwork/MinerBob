package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 19.09.2017.
 */

public class RunningForm {
    Rectangle boundsPause;

    public RunningForm() {
        boundsPause = new Rectangle(GameScreen.WIDTH /  10 * 9, 0, GameScreen.WIDTH / 10, GameScreen.WIDTH / 10);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1);
        shaper.rect(boundsPause.x, boundsPause.y, boundsPause.width, boundsPause.height);
        shaper.end();
    }

    public boolean isClickedPause(float x, float y) {
        return boundsPause.contains(x, y);
    }
}
