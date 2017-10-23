package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 19.10.2017.
 */

public class MoneyAnimation {
    private Vector2 position, velocity, tempVector;
    private float radius;

    public MoneyAnimation() {
        position = new Vector2(0, -100);
        velocity = new Vector2(0, -300);
        tempVector = new Vector2(0, -10);
        this.radius = 2f;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        velocity.x = (GameScreen.WIDTH - x) * 4;
        velocity.y = (0 - y) * 4;
    }

    public void draw(SpriteBatch batcher, ShapeRenderer shaper) {
        if (position.y + radius > 0) {
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(255, 255, 0, 1);
            shaper.circle(position.x, position.y, radius);
            shaper.end();
        }
    }

    public void update(float delta) {
        if (position.y + radius > 0) {
            tempVector.y = velocity.y;
            tempVector.x = velocity.x;
            position.add(tempVector.scl(delta));
        }
    }
}
