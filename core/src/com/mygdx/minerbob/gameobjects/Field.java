package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 16.09.2017.
 */

public class Field {
    private Vector2 positionFirst, positionSecond, velocity, tempVector;

    private float height;
    private boolean isFirst;
    private GameWorld gameWorld;
    private float width;
    private boolean isGreenFirst, isGreenSecond;
    private TextureRegion firstTexture, secondTexture;


    public Field(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        height = this.gameWorld.HEIGHT;
        width = this.gameWorld.WIDTH;

        tempVector = new Vector2(0, 0);
        positionFirst = new Vector2(0, 0);
        positionSecond = new Vector2(0, height);
        velocity = new Vector2(0, -1);
        restart();
    }

    public void restart() {
        isFirst = true;
        positionFirst.set(0, 0);
        positionSecond.set(0, height);
        velocity.set(0, -1);
        secondTexture = gameWorld.assetLoader.bgGreen;
        firstTexture = gameWorld.assetLoader.bgGreenToBlue;
        isGreenFirst = true;
        isGreenSecond = true;
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void update(float delta) {
        tempVector.set(velocity.x, velocity.y);
        positionFirst.add(tempVector.scl(delta));
        tempVector.set(velocity.x, velocity.y);
        positionSecond.add(tempVector.scl(delta));

        if(isFirst && positionFirst.y + height <= 0) {
            gameWorld.isStart = false;
            isFirst = false;
        }

        if (positionFirst.y + height <= 0) {
            positionFirst.y = positionSecond.y + height - 1;
            if (isGreenFirst) {
                firstTexture = gameWorld.assetLoader.bgGreenToBlue;
                isGreenFirst = false;
            } else {
                firstTexture = gameWorld.assetLoader.bgBlueToGreen;
                isGreenFirst = true;
            }
        }

        if (positionSecond.y + height <= 0) {
            positionSecond.y = positionFirst.y + height - 1;
            if (isGreenSecond) {
                secondTexture = gameWorld.assetLoader.bgBlue;
                isGreenSecond = false;
            }
            else {
                secondTexture = gameWorld.assetLoader.bgGreen;
                isGreenSecond = true;
            }
        }
    }

    public void draw(SpriteBatch batcher) {
        if (isFirst)
            batcher.draw(gameWorld.assetLoader.startField, positionFirst.x, positionFirst.y, width, height);
        else
            batcher.draw(firstTexture, positionFirst.x, positionFirst.y, width, height);

        batcher.draw(secondTexture, positionSecond.x, positionSecond.y, width, height);
    }
}
