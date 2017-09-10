package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Алексей on 10.09.2017.
 */

public class Actor {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Rectangle rectangleBounds;

    private int width, height;

    private boolean isAlive;
    private boolean isOnBlock = true;

    public Actor(int x, int y, int width, int height) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        this.width = width;
        this.height = height;
        rectangleBounds = new Rectangle();
        isAlive = true;
    }

    public void update(float delta) {
        if(!isOnBlock) {
            velocity.add(acceleration.cpy().scl(delta));

            if (velocity.y > 200) {
                velocity.y = 200;
            }

            // проверяем потолок
       /* if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }*/

            position.add(velocity.cpy().scl(delta));
            rectangleBounds.set(position.x, position.y, width, height);
        }
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
}
