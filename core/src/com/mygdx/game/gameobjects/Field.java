package com.mygdx.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 16.09.2017.
 */

public class Field {
    private Vector2 positionFirst, positionSecond, velocity, acceleration;

    private float height = GameScreen.HEIGHT;

    public Field() {
        positionFirst = new Vector2(0, 0);
        positionSecond = new Vector2(0, height);
        velocity = new Vector2(0, -10);
    }

    public void update(float delta) {
        positionFirst.add(velocity.cpy().scl(delta));
        positionSecond.add(velocity.cpy().scl(delta));

        if (positionFirst.y + height <= 0)
            positionFirst.y = height;

        if (positionSecond.y + height <= 0)
            positionSecond.y = height;
    }

    public void draw(SpriteBatch batcher) {
        batcher.begin();
        batcher.draw(AssetLoader.bgFirst, positionFirst.x, positionFirst.y, GameScreen.WIDTH, height);
        batcher.draw(AssetLoader.bgFirst, positionSecond.x, positionSecond.y, GameScreen.WIDTH, height);
        batcher.end();
    }
}
