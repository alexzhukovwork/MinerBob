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
    private int count;
    private float beginY;

r
    public MoneyAnimation() {
        position = new Vector2(0, -100);
        velocity = new Vector2(0, -300);
        tempVector = new Vector2(0, -10);
        this.radius = 2f;
    }

    public void setAttributes(float x, float y, int count) {
        this.count = count;
        position.x = x;
        position.y = y;

        beginY = y;
        velocity.x = (GameScreen.WIDTH - x) * 3;
        velocity.y = (0 - y) * 3;
    }

    public void draw(SpriteBatch batcher, ShapeRenderer shaper) {
        if (position.y + count * radius * 2> 0) {
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(255, 255, 0, 1);
            for (int i = 0; i < count; i++) {
                if (beginY - position.y > radius * i * 2)
                    shaper.circle(position.x - i * radius * 2, position.y + i * radius * 2, radius);
            }
            shaper.end();
        }
    }

    public void update(float delta) {
        if (position.y + count * radius * 2 > 0) {
            tempVector.y = velocity.y;
            tempVector.x = velocity.x;
            position.add(tempVector.scl(delta));
        }
    }
}
