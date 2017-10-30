package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 16.09.2017.
 */

public class Field {
    private Vector2 positionFirst, positionSecond, velocity, acceleration;

    private float height = GameScreen.HEIGHT;
    private boolean isFirst;
    private GameWorld gameWorld;

    public Field(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        restart();
    }

    public void restart() {
        isFirst = true;
        positionFirst = new Vector2(0, 0);
        positionSecond = new Vector2(0, height);
        velocity = new Vector2(0, -1);
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void update(float delta) {
        positionFirst.add(velocity.cpy().scl(delta));
        positionSecond.add(velocity.cpy().scl(delta));

        if(isFirst && positionFirst.y + height <= 0) {
            gameWorld.isStart = false;
            isFirst = false;
        }

        if (positionFirst.y + height <= 0)
            positionFirst.y = positionSecond.y + height;

        if (positionSecond.y + height <= 0)
            positionSecond.y = positionFirst.y + height;
    }

    public void draw(SpriteBatch batcher) {
        batcher.begin();

        if (isFirst)
            batcher.draw(gameWorld.assetLoader.startField, positionFirst.x, positionFirst.y, GameScreen.WIDTH, height);
        else
            batcher.draw(gameWorld.assetLoader.bgFirst, positionFirst.x, positionFirst.y, GameScreen.WIDTH, height);

        batcher.draw(gameWorld.assetLoader.bgFirst, positionSecond.x, positionSecond.y, GameScreen.WIDTH, height);
        batcher.end();
    }
}
