package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameobjects.typemode.TypeMode;
import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 10.09.2017.
 */

public class Actor {
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 acceleration;
    private Vector2 tempVector;

    private TypeMode mode;

    private Block blockCurrent = null;

    private GameWorld gameWorld;

    public Rectangle rectangleBounds;

    private float width, height;

    private boolean isAlive = true;
    private boolean isOnBlock = false;
    public boolean hasCurrentBlock = false;
    public boolean isOnLava = false;

    public Actor(float x, float y, float width, float height, GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        tempVector = new Vector2(0 , 100);
        acceleration = new Vector2(0, 100);
        this.width = width;
        this.height = height;
        rectangleBounds = new Rectangle(x, y, width, height);
        isAlive = true;
    }

    public void restart(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 100);
        isAlive = true;
        isOnBlock = false;
        isOnLava = false;
    }

    public void restore(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        isAlive = true;
        isOnBlock = false;
    }

    public void update(float delta) {
        if(!isOnBlock) {
            tempVector.set(acceleration.x, acceleration.y);
            velocity.add(tempVector.scl(delta));

            if (velocity.y > 200) {
                velocity.y = 200;
            }

            tempVector.set(velocity.x, velocity.y);
            position.add(tempVector.scl(delta));

            if (position.y > gameWorld.HEIGHT - height * 3/4) {
                velocity.y = 0;
                position.y = gameWorld.HEIGHT - height * 3/4;
            }

        } else {
            checkAlive();

            if (gameWorld.isCollisedSecond)
                acceleration.y = gameWorld.HEIGHT / 2 * 12f;
            else acceleration.y = 80;


        }

        rectangleBounds.set(position.x, position.y, width, height);
    }

    public void onTouch(float x, float y) {
        mode = mode.getMode();
        mode.execute(x, y);
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
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

    public Boolean getHasCurrentBlock() {
        return hasCurrentBlock;
    }

    public void setCurrentBlock(Block block) {
        this.blockCurrent = block;
    }

    public void setHasCurrentBlock(boolean hasCurrentBlock) {
        this.hasCurrentBlock = hasCurrentBlock;
    }

    public Block getBlock() {
        return blockCurrent;
    }

    public void setAcceleration(float x, float y) {
        acceleration.set(x, y);
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public void setMode(TypeMode typeMode) {
        mode = typeMode;
        typeMode.start();
    }

    public TypeMode getMode() {
        return mode;
    }
}
