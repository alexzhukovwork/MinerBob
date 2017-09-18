package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 10.09.2017.
 */

public class Actor {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private Block block = null;
    private Block blockCurrent = null;

    private Rectangle rectangleBounds;

    private float width, height;

    private boolean isAlive = true;
    private boolean isOnBlock = false;

    public Actor(float x, float y, float width, float height) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 100);
        this.width = width;
        this.height = height;
        rectangleBounds = new Rectangle();
        isAlive = true;
    }

    public void restart(float x, float y, float width, float height) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 100);
        this.width = width;
        this.height = height;
        isAlive = true;
        isOnBlock = false;
    }

    public void update(float delta) {
        if(!isOnBlock) {
            velocity.add(acceleration.cpy().scl(delta));

            if (velocity.y > 200) {
                velocity.y = 200;
            }

            position.add(velocity.cpy().scl(delta));

            if (position.y > GameScreen.HEIGHT - height * 3/4) {
                velocity.y = 0;
                position.y = GameScreen.HEIGHT - height * 3/4;
            }

        } else {
            checkAlive();
            acceleration.y = GameScreen.HEIGHT / 2 * 12f;
        }

        rectangleBounds.set(position.x, position.y, width, height);
    }

    public void onTouch(int screenX, int screenY) {
        if (isOnBlock && isAlive) {
            position.y -= 1;
            rectangleBounds.y -= 1;
            if (screenX > position.x + width) {
                if (position.x + GameScreen.WIDTH / 5 < GameScreen.WIDTH) {
                    velocity.x = GameScreen.WIDTH - 10;
                    block = blockCurrent;
                }
            } else if (screenX < position.x) {
                if (position.x - GameScreen.WIDTH / 5 > 0) {
                    velocity.x = -GameScreen.WIDTH + 10;
                    block = blockCurrent;
                }
            } else
                block = null;
            velocity.y = -GameScreen.HEIGHT;
            acceleration.y = -velocity.y * 10f;
            isOnBlock = false;
        }
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    private void checkAlive() {
        isAlive = !(position.y + height < 0);
    }

    public Rectangle getRectangleBounds() {
        return rectangleBounds;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRectX() {
        return rectangleBounds.x;
    }

    public float getRectY() {
        return rectangleBounds.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public boolean getOnBlock() {
        return isOnBlock;
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setRectangleBounds(float x, float y, float width, float height) {
        rectangleBounds.set(x, y, width, height);
    }

    public void setOnBlock(boolean isOnBlock) {
        this.isOnBlock = isOnBlock;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Block getBlock() {
        return block;
    }

    public void setCurrentBlock(Block block) {
        this.blockCurrent = block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setAcceleration(float x, float y) {
        acceleration.set(x, y);
    }
}
