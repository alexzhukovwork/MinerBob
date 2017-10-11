package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 19.09.2017.
 */

public class RunningForm {
    Rectangle boundsPause;

    public RunningForm() {
        boundsPause = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, 0, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1f);
        shaper.rect(boundsPause.x, boundsPause.y, boundsPause.width, boundsPause.height);
        shaper.end();
    }

    public boolean isClickedPause(float x, float y) {
        return boundsPause.contains(x, y);
    }
}
