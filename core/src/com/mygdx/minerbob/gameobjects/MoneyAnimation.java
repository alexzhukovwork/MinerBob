package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 19.10.2017.
 */

public class MoneyAnimation {
    private Vector2 position, velocity, tempVector;
    private float width;
    private int count;
    private float beginY;
    private GameWorld gameWorld;


    public MoneyAnimation(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        position = new Vector2(0, -100);
        velocity = new Vector2(0, -300);
        tempVector = new Vector2(0, -10);
        this.width = 4f;
    }

    public void setAttributes(float x, float y, int count) {
        this.count = count;
        position.x = x;
        position.y = y;

        beginY = y;
        velocity.x = (gameWorld.WIDTH - x) * 3;
        velocity.y = (0 - y) * 3;
    }

    public void draw(SpriteBatch batcher, ShapeRenderer shaper) {
        if (position.y + count * width > 0) {
          //  shaper.begin(ShapeRenderer.ShapeType.Filled);
           // shaper.setColor(255, 255, 0, 1);
            batcher.begin();
            for (int i = 0; i < count; i++) {
                if (beginY - position.y > width * i)
                    batcher.draw(gameWorld.assetLoader.moneyTexture,
                            position.x - i * width, position.y + i * width, width, width);
                   // shaper.circle(position.x - i * width * 2, position.y + i * width * 2, width);
            }

            batcher.end();
           // shaper.end();
        }
    }

    public void update(float delta) {
        if (position.y + count * width * 2 > 0) {
            tempVector.y = velocity.y;
            tempVector.x = velocity.x;
            position.add(tempVector.scl(delta));
        }
    }
}
